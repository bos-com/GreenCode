# GreenCode

GreenCode is a full-stack system designed to support environmental, sustainability, and community-impact projects under Bos-Com.  
It consists of a **Spring Boot backend API**. A React frontend integration is planned for a future release.

---

## 🚀 Features

### Backend (Spring Boot)
- RESTful API
- JWT/OAuth authentication
- PostgreSQL database support
- Centralised configuration (`config/`, `.env`)
- Dockerized for easy deployment
- Swagger/OpenAPI documentation

### Frontend (React) (planned)
- Modern React (Create React App)
- React Router for navigation
- Axios for API communication
- Authentication UI (login, password reset flow)
- Responsive UI with Tailwind CSS (recommended)
- Ready to connect to backend reset API

---

## 📁 Project Structure
GreenCode/
├── src/                 # Spring Boot source code
├── config/              # external configuration & scripts
├── scripts/             # helper scripts
├── docs/                # architecture, API docs
├── pom.xml              # Maven build file
└── docker-compose.yml   # Docker orchestration

---
## 🚀 Getting Started (Backend)
### Prerequisites
- Java 17+
- Maven
- Docker (optional, if you want to run via `docker-compose.yml`)

### Configure environment variables
Copy the example environment file and adjust values as needed:
```bash
# Linux/macOS/Git Bash
cp env.example .env

# Windows PowerShell
Copy-Item env.example .env
```

### Run locally (no Docker)
```bash
mvn clean install
mvn spring-boot:run
```

### Run with Docker Compose (optional)
```bash
docker-compose up -d --build
```

Note: this repository’s `Dockerfile` expects a Maven wrapper (`mvnw`). If your clone does not include `mvnw`, run locally with Maven instead (see above) or update the `Dockerfile`.


