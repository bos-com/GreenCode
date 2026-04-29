# GreenCode

GreenCode is a Spring Boot backend for environmental, sustainability, and
community-impact projects under BOS-COM. The service provides a foundation for
user management, project data, API documentation, and containerized deployment.

## Features

- Spring Boot REST API with a `/api` context path
- User management endpoints backed by Spring Data JPA
- H2 database for local development and PostgreSQL support for deployment
- Spring Security, validation, and actuator health endpoints
- Swagger/OpenAPI UI for interactive API exploration
- Docker and Docker Compose assets for repeatable local environments

## Project Structure

```text
GreenCode/
├── config/                 # Docker, nginx, and monitoring configuration
├── docs/                   # Architecture, API, and deployment notes
├── scripts/                # Setup, deploy, and backup helpers
├── src/main/java/          # Spring Boot application source
├── src/main/resources/     # Application configuration
├── src/test/java/          # Automated tests
├── docker-compose.yml      # Local service orchestration
├── Dockerfile              # Backend container image
└── pom.xml                 # Maven build configuration
```

## Prerequisites

- Java 17+
- Maven 3.9+
- Docker and Docker Compose, if you want to run the containerized stack

## Local Development

1. Clone the repository:

   ```bash
   git clone https://github.com/bos-com/GreenCode.git
   cd GreenCode
   ```

2. Review the example environment file:

   ```bash
   cp env.example .env
   ```

3. Run the backend:

   ```bash
   mvn spring-boot:run
   ```

4. Open the local API tooling:

   - API base URL: `http://localhost:8080/api`
   - Swagger UI: `http://localhost:8080/api/swagger-ui.html`
   - Actuator health: `http://localhost:8080/api/actuator/health`
   - H2 console: `http://localhost:8080/api/h2-console`

## Docker Development

```bash
docker-compose up --build
```

Use Docker Compose when you want a closer match to the deployment topology. See
[`docs/deployment/README.md`](docs/deployment/README.md) for deployment notes.

## Testing

```bash
mvn test
```

Run the test suite before opening a pull request, especially when touching
controllers, services, configuration, or entity validation.

## Documentation

- [`docs/api/README.md`](docs/api/README.md) describes the API surface.
- [`docs/architecture/README.md`](docs/architecture/README.md) links the
  architecture documentation set.
- [`docs/deployment/README.md`](docs/deployment/README.md) covers deployment
  expectations.

## Contributing

1. Open or pick a GitHub issue that describes the improvement.
2. Create a focused branch from `main`.
3. Keep changes scoped to one bug fix, feature, or documentation improvement.
4. Run relevant tests and document any commands that could not be run.
5. Open a pull request that links the issue and explains the user impact.

## License

This project is licensed under the terms in [`LICENSE`](LICENSE).
