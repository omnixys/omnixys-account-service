package com.omnixys.account.messaging;

import lombok.RequiredArgsConstructor;

/**
 * Zentrale Konfiguration der Kafka-Topic-Namen.
 * <p>
 * Die Namen folgen dem Schema: {@code <service>.<events>.<service>}.
 * </p>
 *
 * @author <a href="mailto:caleb-script@outlook.de">Caleb Gyamfi</a>
 * @since 07.05.2025
 */
@RequiredArgsConstructor
public final class KafkaTopicProperties {

    public static final String TOPIC_NOTIFICATION_CREATE_ACCOUNT = "notification.create.account";
    public static final String TOPIC_NOTIFICATION_DELETE_ACCOUNT = "notification.delete.account";

    public static final String TOPIC_ACCOUNT_CREATE_PERSON = "account.create.person";
    public static final String TOPIC_ACCOUNT_DELETE_PERSON = "account.delete.person";
    
    public static final String TOPIC_LOG_STREAM_LOG_ACCOUNT = "log-Stream.log.account";

    public static final String TOPIC_ACCOUNT_SHUTDOWN_ORCHESTRATOR = "account.shutdown.orchestrator";
    public static final String TOPIC_ACCOUNT_START_ORCHESTRATOR = "account.start.orchestrator";
    public static final String TOPIC_ACCOUNT_RESTART_ORCHESTRATOR = "account.restart.orchestrator";

    public static final String TOPIC_ALL_SHUTDOWN_ORCHESTRATOR = "all.shutdown.orchestrator";
    public static final String TOPIC_ALL_START_ORCHESTRATOR = "all.start.orchestrator";
    public static final String TOPIC_ALL_RESTART_ORCHESTRATOR = "all.restart.orchestrator";
}
