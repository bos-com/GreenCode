# GreenCode Deployment Guide

This directory contains deployment notes for the current GreenCode backend.

## Deployment Options

### Docker Compose

- File: `docker-compose.yml`
- Best for: local full-stack development
- Command:

  ```bash
  cp env.example .env
  docker-compose up --build -d
  ```

### Standalone JAR

- Best for: direct server deployments
- Build command:

  ```bash
  mvn clean package
  java -jar target/greencode-backend-1.0.0.jar
  ```

### Helper Script

- File: `scripts/deploy.sh`
- Best for: scripted local deployments with environment and port options

## Profiles

| Profile | Database | Intended use |
| --- | --- | --- |
| `dev` | H2 in-memory | Local development |
| `staging` | PostgreSQL | Pre-production validation |
| `prod` | PostgreSQL | Production deployments |

## Prerequisites

- Java 17+
- Maven 3.6+
- Optional: Docker and Docker Compose

## Health and Monitoring Endpoints

- Application health: `/api/actuator/health`
- Metrics: `/api/actuator/metrics`
- Info: `/api/actuator/info`
- Swagger UI: `/api/swagger-ui.html`

If the bundled Nginx configuration is used, the proxy also serves `/health` for
container-level checks. Treat that as a reverse-proxy heartbeat, not as the
Spring Boot actuator endpoint.
