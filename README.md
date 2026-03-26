# GreenCode

GreenCode is a full-stack system designed to support environmental, sustainability, and community-impact projects under Bos-Com.  
It consists of a **Spring Boot backend API** and a **React frontend** (new addition), with future integrations planned.

---

## Features

### Backend (Spring Boot)
- RESTful API
- JWT/OAuth authentication
- PostgreSQL database support
- Centralised configuration (`config/`, `.env`)
- Dockerized for easy deployment
- Swagger/OpenAPI documentation

### Frontend (React)
- Modern React (Create React App)
- React Router for navigation
- Axios for API communication
- Authentication UI (login, password reset flow)
- Responsive UI with Tailwind CSS (recommended)
- Ready to connect to backend reset API

---

## Project Structure
- `src/` - Spring Boot source code
- `config/` - External configuration and scripts
- `docs/` - Architecture and API docs
- `scripts/` - Project scripts
- `BOS/` - Standalone BOS project
- `LifeLine-ICT/` - Standalone LifeLine-ICT project
- `OpenCare-Core/` - Standalone OpenCare-Core project
- `pom.xml` - Maven build file
- `Dockerfile` - Docker image definition
- `docker-compose.yml` - Docker orchestration
- `env.example` - Environment variable template
- `LICENSE` - License details



