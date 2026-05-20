# GreenCode

GreenCode is a Spring Boot backend API for environmental and sustainability
project management. It provides a foundation for tracking projects, users,
configuration, deployment assets, and future integrations for environmental
impact workflows.

## Features

- REST API built with Spring Boot.
- JWT/OAuth-ready security dependencies.
- PostgreSQL support for shared environments.
- H2-friendly local development profile.
- Centralized environment configuration through `env.example`.
- Docker and Docker Compose assets for deployment.
- Architecture, API, and deployment documentation under `docs/`.

## Project Structure

```text
GreenCode/
├── src/                 # Spring Boot source and tests
├── config/              # External service configuration
├── docs/                # Architecture, API, and deployment docs
├── scripts/             # Setup, deployment, and backup scripts
├── pom.xml              # Maven build file
├── env.example          # Environment variable reference
└── docker-compose.yml   # Local/deployment service orchestration
```

## Prerequisites

- Java 17 or newer.
- Maven 3.9 or newer.
- Docker and Docker Compose for containerized setup.
- PostgreSQL when running outside the default development profile.

## Local Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/bos-com/GreenCode.git
   cd GreenCode
   ```

2. Copy the environment template:

   ```bash
   cp env.example .env
   ```

3. Review the values in `.env`, especially database, JWT, CORS, and logging
   settings.

4. Run the backend locally:

   ```bash
   mvn spring-boot:run
   ```

5. Run tests before opening a pull request:

   ```bash
   mvn test
   ```

## Docker Setup

Use Docker Compose when you want the backend and supporting services to run in a
repeatable local environment:

```bash
docker-compose up --build
```

Stop the stack with:

```bash
docker-compose down
```

## Documentation

- API documentation: [`docs/api/README.md`](docs/api/README.md)
- Architecture overview: [`docs/architecture/README.md`](docs/architecture/README.md)
- Deployment guide: [`docs/deployment/README.md`](docs/deployment/README.md)

## Contributing

Start with [CONTRIBUTING.md](CONTRIBUTING.md) for branch naming, commit style,
pull request expectations, and review guidance.

## License

This project is licensed under the terms in [LICENSE](LICENSE).
