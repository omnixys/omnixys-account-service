# omnixys-account-service

Der **Omnixys Account Service** ist ein Java-basierter Microservice zur Verwaltung von Bankkonten innerhalb des modularen **OmnixysSphere**-Ökosystems. Er ermöglicht das Anlegen, Pflegen und Löschen verschiedener Kontotypen (z. B. Girokonto, Sparkonto, Tagesgeld, Kreditkonto) für Benutzer mit voller Unterstützung von Kafka, GraphQL (Schema First), Tracing und Sicherheitsfunktionen.

---

## 🚀 Features

- Erstellung, Aktualisierung und Löschung von Bankkonten
- Unterstützung mehrerer Kontotypen pro Benutzer
- Zuweisung zu Personenprofilen aus dem Person-Service
- Ereignisbasierte Kommunikation mit Orchestratoren und Notification-Services
- Vollständige Unterstützung für verteiltes Tracing mit OpenTelemetry
- Integriertes Logging über Kafka im JSON-Format (LoggerPlus)
- Absicherung von GraphQL-Endpunkten per JWT- Speicherung und Validierung von Zugangsdaten (Passwort-Hashing)
- Rollen- und Berechtigungsverwaltung über Keycloak
- Vollständige Unterstützung für verteiltes Tracing mit OpenTelemetry
- Integriertes Logging über Kafka im JSON-Format (LoggerPlus)
- Absicherung von GraphQL-Endpunkten per JWT

---

## 🛠️ Technologie-Stack

- **Spring Boot 23 (Java)**
- **GraphQL (Schema First)** via `graphql-java-tools`
- **PostgreSQL** als relationale Datenbank
- **Kafka** für Logging- und Event-Kommunikation
- **Keycloak** für Authentifizierung & Autorisierung
- **OpenTelemetry** + Tempo für Tracing
- **Prometheus** + Grafana für Metriken
- **LoggerPlus** für zentrales Logging

---

## 📡 Kafka-Kommunikation

### 🔄 Events Produced

- `notification.create.account`
- `notification.delete.account`
- `log.account`

### 📥 Events Consumed

- `account.create.person`
- `account.delete.person`
- `account.shutdown.orchestratore`
- `account.start.orchestratore`
- `account.restart.orchestratore`

---

## 🔌 Port-Konvention

| Umgebung | Port  |
|----------|-------|
| Lokal    | 7002  |
| Docker   | 7002  |

> Siehe [port-konvention.md](../port-konvention.md) für Details zur Portstruktur im Omnixys-Ökosystem.

---

## 📦 Projektstruktur

```bash
src/main/java/com/omnixys/account/
├── controller/
├── graphql/
├── service/
├── model/
├── repository/
└── security/
```

---

## 🧪 Tests

- Unit Tests: JUnit 5 + Mockito
- Coverage: JaCoCo
- Qualitätssicherung via SonarQube (CI)

---

## 🐳 Schnellstart via Docker

```bash
docker-compose up --build
```

Oder manuell:

```bash
./gradlew bootRun
```

---

## 🤝 Beitrag leisten

Bitte lies die [CONTRIBUTING.md](../CONTRIBUTING.md), bevor du einen PR erstellst. Wir freuen uns über:

- ✨ Neue Features
- 🐛 Bugfixes
- 📘 Verbesserte Doku
- 🧪 Zusätzliche Tests

---

## 🔐 Sicherheit

Sicherheitslücken? Bitte **nicht öffentlich** melden. Stattdessen:  
📧 [security@omnixys.com](mailto:security@omnixys.com)

---

## 📜 Lizenz

Dieses Projekt steht unter der [GNU GPLv3 Lizenz](../LICENSE.md)  
© 2025 [Omnixys – The Fabric of Modular Innovation](https://omnixys.com)
