package com.omnixys.account.messaging;

import com.omnixys.account.models.event.CreateAccountDTO;
import com.omnixys.account.models.event.DeleteAccountDTO;
import com.omnixys.account.models.inputs.CreateAccountInput;
import com.omnixys.account.models.mapper.AccountMapper;
import com.omnixys.account.service.AccountWriteService;
import com.omnixys.account.tracing.LoggerPlus;
import com.omnixys.account.tracing.LoggerPlusFactory;
import io.micrometer.observation.annotation.Observed;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanBuilder;
import io.opentelemetry.api.trace.SpanContext;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.TraceFlags;
import io.opentelemetry.api.trace.TraceState;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

import static com.omnixys.account.messaging.KafkaTopicProperties.TOPIC_ACCOUNT_CREATE_PERSON;
import static com.omnixys.account.messaging.KafkaTopicProperties.TOPIC_ACCOUNT_DELETE_PERSON;
import static com.omnixys.account.messaging.KafkaTopicProperties.TOPIC_ACCOUNT_RESTART_ORCHESTRATOR;
import static com.omnixys.account.messaging.KafkaTopicProperties.TOPIC_ACCOUNT_SHUTDOWN_ORCHESTRATOR;
import static com.omnixys.account.messaging.KafkaTopicProperties.TOPIC_ACCOUNT_START_ORCHESTRATOR;
import static com.omnixys.account.messaging.KafkaTopicProperties.TOPIC_ALL_RESTART_ORCHESTRATOR;
import static com.omnixys.account.messaging.KafkaTopicProperties.TOPIC_ALL_SHUTDOWN_ORCHESTRATOR;
import static com.omnixys.account.messaging.KafkaTopicProperties.TOPIC_ALL_START_ORCHESTRATOR;

/**
 * Kafka-Consumer für eingehende Events zur Kontoerstellung und -löschung.
 *
 * <p>
 * Unterstützt folgende Nachrichtenformate:
 * <ul>
 *     <li>{@link CreateAccountInput} auf <code>account.customer.created</code></li>
 *     <li>{@link DeleteAccountDTO} auf <code>account.customer.deleted</code></li>
 * </ul>
 * </p>
 *
 * @author
 * @since 21.04.2025
 */
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final ApplicationContext context;
    private final AccountWriteService accountWriteService;
    private final AccountMapper accountMapper;
    private final Tracer tracer;
    private final LoggerPlusFactory factory;
    private LoggerPlus logger() {
        return factory.getLogger(getClass());
    }

    /**
     * Konsumiert neue Kunden-Ereignisse und erstellt ein Konto.
     *
     * @param record Kafka-Record mit JSON-Payload vom Typ {@link CreateAccountInput}
     */
    @KafkaListener(topics = TOPIC_ACCOUNT_CREATE_PERSON, groupId = "${app.groupId}")
    @Observed(name = "kafka-consume.account-customer-created")
    public void consumeCustomerCreated(ConsumerRecord<String, CreateAccountDTO> record) {
        final var headers = record.headers();
        final var dto = record.value();
        final var createAccountInput = accountMapper.toCreateInput(dto);

        // ✨ 1. Extrahiere traceparent Header (W3C) oder B3 als Fallback
        final var traceParent = getHeader(headers, "traceparent");

        SpanContext linkedContext = null;
        if (traceParent != null && traceParent.startsWith("00-")) {
            String[] parts = traceParent.split("-");
            if (parts.length == 4) {
                String traceId = parts[1];
                String spanId = parts[2];
                boolean sampled = "01".equals(parts[3]);

                linkedContext = SpanContext.createFromRemoteParent(
                    traceId,
                    spanId,
                    sampled ? TraceFlags.getSampled() : TraceFlags.getDefault(),
                    TraceState.getDefault()
                );
            }
        }

        // ✨ 2. Starte neuen Trace mit Link (nicht als Parent!)
        SpanBuilder spanBuilder = tracer.spanBuilder("kafka.account.consume")
            .setSpanKind(SpanKind.CONSUMER)
            .setAttribute("messaging.system", "kafka")
            .setAttribute("messaging.destination", TOPIC_ACCOUNT_CREATE_PERSON)
            .setAttribute("messaging.operation", "consume");

        if (linkedContext != null && linkedContext.isValid()) {
            spanBuilder.addLink(linkedContext);
        }

        Span span = spanBuilder.startSpan();

        try (Scope scope = span.makeCurrent()) {
            assert scope != null;
            logger().info("📥 Empfangene Nachricht auf '{}': {}", TOPIC_ACCOUNT_CREATE_PERSON, dto);
            final var accountInput = accountMapper.toAccount(createAccountInput);
            final var account = accountWriteService.create(accountInput);
            logger().info("✅ Konto erstellt für Benutzername='{}', Account-ID={}", createAccountInput.username(), account.getId());
            span.setStatus(StatusCode.OK);
        } catch (Exception e) {
            span.recordException(e);
            span.setStatus(StatusCode.ERROR, "Kafka-Fehler");
            logger().error("❌ Fehler beim Erstellen des Kontos", e);
        } finally {
            span.end();
        }
    }

    private String getHeader(Headers headers, String key) {
        Header header = headers.lastHeader(key);
        return header != null ? new String(header.value(), StandardCharsets.UTF_8) : null;
    }

    /**
     * Konsumiert Lösch-Events und entfernt die zugehörigen Accounts.
     *
     * @param record Kafka-Record mit JSON-Payload vom Typ {@link DeleteAccountDTO}
     */
    @Observed(name = "kafka-consume.account-customer-deleted")
    @KafkaListener(topics = TOPIC_ACCOUNT_DELETE_PERSON, groupId = "${app.groupId}")
    public void consumeCustomerDeleted(ConsumerRecord<String, DeleteAccountDTO> record) {
        Headers headers = record.headers();
        final var deleteAccountDTO = record.value();

        // ✨ 1. Extrahiere traceparent Header (W3C) oder B3 als Fallback
        String traceParent = getHeader(headers, "traceparent");

        SpanContext linkedContext = null;
        if (traceParent != null && traceParent.startsWith("00-")) {
            String[] parts = traceParent.split("-");
            if (parts.length == 4) {
                String traceId = parts[1];
                String spanId = parts[2];
                boolean sampled = "01".equals(parts[3]);

                linkedContext = SpanContext.createFromRemoteParent(
                    traceId,
                    spanId,
                    sampled ? TraceFlags.getSampled() : TraceFlags.getDefault(),
                    TraceState.getDefault()
                );
            }
        }

        // ✨ 2. Starte neuen Trace mit Link (nicht als Parent!)
        SpanBuilder spanBuilder = tracer.spanBuilder("kafka.account.consume")
            .setSpanKind(SpanKind.CONSUMER)
            .setAttribute("messaging.system", "kafka")
            .setAttribute("messaging.destination", TOPIC_ACCOUNT_DELETE_PERSON)
            .setAttribute("messaging.operation", "consume");

        if (linkedContext != null && linkedContext.isValid()) {
            spanBuilder.addLink(linkedContext);
        }

        Span span = spanBuilder.startSpan();
        try (Scope scope = span.makeCurrent()) {
            assert scope != null;
            logger().info("📥 Empfangene Nachricht auf '{}': {}", TOPIC_ACCOUNT_DELETE_PERSON, deleteAccountDTO);
            accountWriteService.deleteAccountByUsernameOrCustomerId(deleteAccountDTO.id(), deleteAccountDTO.username());
            logger().info("🗑️ Konto gelöscht: username='{}', customerId={}", deleteAccountDTO.username(), deleteAccountDTO.id());
            span.setStatus(StatusCode.OK);
        } catch (Exception e) {
            span.recordException(e);
            span.setStatus(StatusCode.ERROR, "Kafka-Fehler");
            logger().error("❌ Fehler beim Erstellen des Kontos", e);
        } finally {
            span.end();
        }
    }

    @Observed(name = "kafka-consume.person.orchestration")
    @KafkaListener(
        topics = {
            TOPIC_ACCOUNT_SHUTDOWN_ORCHESTRATOR,
            TOPIC_ACCOUNT_START_ORCHESTRATOR,
            TOPIC_ACCOUNT_RESTART_ORCHESTRATOR
        },
        groupId = "${app.groupId}"
    )
    public void handlePersonScoped(ConsumerRecord<String, String> record) {
        final String topic = record.topic();
        logger().info("Person-spezifisches Kommando empfangen: {}", topic);

        switch (topic) {
            case TOPIC_ACCOUNT_SHUTDOWN_ORCHESTRATOR -> shutdown();
            case TOPIC_ACCOUNT_RESTART_ORCHESTRATOR -> restart();
            case TOPIC_ACCOUNT_START_ORCHESTRATOR -> logger().info("Startsignal für Person-Service empfangen");
        }
    }

    @Observed(name = "kafka-consume.all.orchestration")
    @KafkaListener(
        topics = {
            TOPIC_ALL_SHUTDOWN_ORCHESTRATOR,
            TOPIC_ALL_START_ORCHESTRATOR,
            TOPIC_ALL_RESTART_ORCHESTRATOR
        },
        groupId = "${app.groupId}"
    )
    public void handleGlobalScoped(ConsumerRecord<String, String> record) {
        final String topic = record.topic();
        logger().info("Globales Systemkommando empfangen: {}", topic);

        switch (topic) {
            case TOPIC_ALL_SHUTDOWN_ORCHESTRATOR -> shutdown();
            case TOPIC_ALL_RESTART_ORCHESTRATOR -> restart();
            case TOPIC_ALL_START_ORCHESTRATOR -> logger().info("Globales Startsignal empfangen");
        }
    }

    private void shutdown() {
        try {
            logger().info("→ Anwendung wird heruntergefahren (Shutdown-Kommando).");
            ((ConfigurableApplicationContext) context).close();
        } catch (Exception e) {
            logger().error("Fehler beim Shutdown: {}", e.getMessage(), e);
        }
    }


    private void restart() {
        logger().info("→ Anwendung wird neugestartet (Restart-Kommando).");
        ((ConfigurableApplicationContext) context).close();
        // Neustart durch externen Supervisor erwartet
    }
}
