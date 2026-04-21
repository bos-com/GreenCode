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
Issue #56
### 1. Prerequisites
- *Java:* Version 17 or higher.
- *Node.js:* For the React frontend.
- *Database:* PostgreSQL installed and running.
- *Build Tool:* Maven.

### 2. Backend Setup
1. Navigate to the root directory.
2. Configure your database details in the env.example file and rename it to .env.
3. Run mvn install to download dependencies.
4. Run mvn spring-boot:run to start the backend.

### 3. Frontend Setup
1. Navigate to the greencode-frontend directory.
2. Run npm install.
3. Run npm start to launch the UI.

