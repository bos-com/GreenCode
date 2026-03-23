# GreenCode

GreenCode is a Spring Boot backend for environmental, sustainability, and community-impact projects under Bos-Com.

The current repository contains the backend service and deployment/configuration files. A frontend is planned but not part of this repository yet.

## Features

- RESTful API with Spring Boot 3
- Spring Security and JWT foundation
- H2 (dev) and PostgreSQL (runtime) support
- Swagger/OpenAPI docs
- Docker Compose setup for local infrastructure
- Monitoring stack definitions (Prometheus/Grafana)

## Prerequisites

Install the following tools before setup:

- Java 21 (required by `pom.xml`)
- Maven 3.9+
- Docker Engine + Docker Compose plugin (`docker compose`) for containerized setup
- PostgreSQL 15+ (for local non-Docker PostgreSQL workflow)
- Node.js 20+ (optional for future frontend work)

## Setup and Installation

### 1. Clone and Prepare Environment

```bash
git clone <repository-url>
cd GreenCode
cp env.example .env
```

### 2. Docker Setup (Recommended)

This starts backend + PostgreSQL + Redis + Adminer:

```bash
docker compose up --build -d postgres redis greencode-backend adminer
```

Check status:

```bash
docker compose ps
docker compose logs -f greencode-backend
```

Stop services:

```bash
docker compose down
```

Useful URLs:

- Backend API base: `http://localhost:8080/api`
- Swagger UI: `http://localhost:8080/api/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/api/api-docs`
- Actuator health: `http://localhost:8080/api/actuator/health`
- H2 console: `http://localhost:8080/api/h2-console`
- Adminer: `http://localhost:8081`

### 3. Local Development (Without Docker)

#### Option A: Fast local development (H2, default)

```bash
mvn clean install
mvn spring-boot:run
```

#### Option B: Local PostgreSQL

1. Create a database (example):

```bash
createdb greencode
```

2. Export runtime overrides:

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/greencode
export SPRING_DATASOURCE_USERNAME=postgres
export SPRING_DATASOURCE_PASSWORD=password
export SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect
```

3. Run the app:

```bash
mvn spring-boot:run
```

### 4. Verify the Application

```bash
curl http://localhost:8080/api/actuator/health
```

Then open Swagger:

`http://localhost:8080/api/swagger-ui.html`

## Production Build Instructions

### Build JAR

```bash
mvn clean package -DskipTests
```

### Run JAR (production profile)

```bash
java -jar target/greencode-backend-1.0.0.jar \
  --spring.profiles.active=prod \
  --server.port=8080
```

For production, configure PostgreSQL credentials via environment variables (or externalized Spring config) before startup.

## Project Structure

```text
GreenCode/
├── src/                 # Spring Boot source code
├── config/              # Docker, nginx, monitoring config
├── docs/                # architecture and API docs
├── scripts/             # setup/deploy/backup scripts
├── pom.xml              # Maven build file
├── Dockerfile           # container build definition
└── docker-compose.yml   # local service orchestration
```
