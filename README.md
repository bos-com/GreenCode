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


## 🛠️ Using Maven Wrapper

This project includes the Maven Wrapper (`mvnw`) to ensure consistent builds across all environments without requiring Maven to be installed globally.

### Why Use Maven Wrapper?
- ✅ No need to install Maven separately
- ✅ Everyone uses the same Maven version
- ✅ Builds are reproducible
- ✅ Works on CI/CD pipelines

### Commands

```bash
# On Linux/Mac
./mvnw clean install
./mvnw spring-boot:run
./mvnw test

# On Windows
mvnw.cmd clean install
mvnw.cmd spring-boot:run
mvnw.cmd test


### Step 4: Commit and push

```bash
git add README.md
git commit -m "docs: add Maven Wrapper usage instructions for issue #27

- Added mvnw commands for Linux/Mac/Windows
- Explained benefits of using Maven Wrapper
- Added instructions to generate wrapper if missing"
git push origin fix/issue-27-maven-wrapper
