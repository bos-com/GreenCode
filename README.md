# GreenCode

GreenCode is a Spring Boot backend project for sustainability and
community-impact services under Bos-Com. The current tracked codebase focuses on
the backend API, deployment configuration, and supporting documentation needed
to run and extend the service.

## Features

- REST API built with Spring Boot
- JPA persistence with H2 for development and PostgreSQL for deployed setups
- Spring Security configuration and role-aware user model
- Actuator endpoints for health and metrics
- Docker and Docker Compose support
- Swagger/OpenAPI integration through Springdoc

## Repository Layout

| Path | Purpose |
| --- | --- |
| `src/` | Spring Boot source code and tests |
| `config/` | Nginx and monitoring configuration |
| `docs/` | API, architecture, and deployment guides |
| `scripts/` | Local setup and deployment helpers |
| `pom.xml` | Maven build definition |
| `docker-compose.yml` | Multi-service development stack |
| `env.example` | Example environment variables used by scripts and deployments |

## Prerequisites

- Java 17 or newer
- Maven 3.6 or newer
- Optional: Docker and Docker Compose for container-based setup

## Local Setup

Use this path when you want to run the Spring Boot app directly on your machine.

1. Clone the repository and enter the project directory:

   ```bash
   git clone https://github.com/bos-com/GreenCode.git
   cd GreenCode
   ```

2. Optional: copy the environment template if you plan to use the helper
   scripts or maintain deployment-specific values in a local `.env` file:

   ```bash
   cp env.example .env
   ```

3. Run the automated tests:

   ```bash
   mvn test
   ```

4. Start the application:

   ```bash
   mvn spring-boot:run
   ```

The default development profile uses the in-memory H2 configuration from
`src/main/resources/application.yml`, so a separate database is not required
for a first local run.

## Docker Setup

Use Docker Compose when you want a full stack with PostgreSQL, Redis, Nginx,
Prometheus, and Grafana.

```bash
cp env.example .env
docker-compose up --build -d
```

## Common Access Points

- Swagger UI: `http://localhost:8080/api/swagger-ui.html`
- OpenAPI spec: `http://localhost:8080/api/api-docs`
- Application health: `http://localhost:8080/api/actuator/health`
- H2 console (dev profile): `http://localhost:8080/api/h2-console`

If you are routing traffic through the bundled Nginx configuration, the proxy
also exposes a lightweight `/health` endpoint for container-level checks. That
proxy endpoint is separate from the Spring Boot actuator health endpoint above.

## Current API Surface

The current tracked controller layer exposes user-management endpoints under
`/api/users`. The repository does not yet contain a dedicated authentication
controller, so any future auth-specific routes should be documented only after
their implementation lands in source control.

## Contributing

Contribution setup, workflow, and pull request expectations are documented in
[`CONTRIBUTING.md`](CONTRIBUTING.md).
