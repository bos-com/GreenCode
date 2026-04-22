# GreenCode

GreenCode is an API core system designed to support esm_greencode .
It consists of a **Spring Boot backend API** 

---

## 🚀 Features

### Backend (Spring Boot)
- RESTful API
- JWT/OAuth authentication
- PostgreSQL database support
- Centralised configuration (`config/`, `.env`)
- Dockerized for easy deployment
- Swagger/OpenAPI documentation


## 📁 Project Structure
GreenCode/
├── src/                 # Spring Boot source code
├── config/              # external configuration & scripts
├── docs/                # architecture, API docs
├── greencode-frontend/  # React frontend (new)
├── pom.xml              # Maven build file
└── docker-compose.yml   # Docker orchestration




## Simplified Architecture Guide (For Beginners)

GreenCode is built using three main layers:

1. **Frontend** — The React user interface that people interact with in the browser.
2. **Backend API** — A Spring Boot application that handles all business logic and data processing.
3. **Database** — PostgreSQL stores all data permanently, while H2 is used for testing.

These three layers communicate with each other over HTTP using REST APIs.
When a user clicks a button on the frontend, a request is sent to the backend,
which then reads or writes data to the database and sends a response back.
