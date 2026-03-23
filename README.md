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


### 🛠️ Frontend Setup & Launch
To get the React frontend running locally:
1. Navigate to the frontend directory: `cd greencode-frontend`
2. Install dependencies: `npm install`
3. Start the development server: `npm start`
4. Access the UI at `http://localhost:3000`
