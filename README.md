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

## Installation

### Prerequisites
Make sure you have the following installed:
- Java 17+
- Maven
- Node.js & npm
- PostgreSQL
- Docker (optional)

---

### 1. Clone the repository
git clone https://github.com/El-Fati/GreenCode.git

cd GreenCode

---

### 2. Backend Setup (Spring Boot)

1. Navigate to the backend directory (root folder):
   cd GreenCode

2. Configure environment variables:
   - Update `.env` or files in the `config/` folder with your database settings

3. Build the project:
   mvn clean install

4. Run the backend:
   mvn spring-boot:run

Backend will run on:
http://localhost:8080

---

### 3. Frontend Setup (React)

1. Navigate to frontend:
   cd greencode-frontend

2. Install dependencies:
   npm install

3. Start the frontend:
   npm start

Frontend will run on:
http://localhost:3000

---

### 4. Run with Docker (Optional)

If Docker is configured:

docker-compose up --build

---

## Usage

1. Start the backend server.
2. Start the frontend application.
3. Open your browser and go to:
   http://localhost:3000

4. Use the application features:
   - Register/Login (authentication system)
   - Navigate through pages using the UI
   - Interact with backend APIs

5. API Documentation:
   Access Swagger UI at:
   http://localhost:8080/swagger-ui.html

---

## Contribution

Contributions are welcome!

To contribute:
1. Fork the repository
2. Create a new branch (e.g., feature/improve-readme or fix/api-bug)
3. Make your changes
4. Commit your changes with clear messages (e.g., docs: improve setup guide)
5. Push to your fork
6. Open a Pull Request

---

## 📌 Notes

- Ensure PostgreSQL is running before starting the backend
- Update database credentials in configuration files if needed
- Frontend communicates with backend via REST APIs