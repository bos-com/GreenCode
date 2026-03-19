# GreenCode

GreenCode is a full-stack system designed to support environmental, sustainability, and community-impact projects under Bos-Com.  
It consists of a **Spring Boot backend API** and a **React frontend** (new addition), with future integrations planned.

---

## 🚀 Features

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
