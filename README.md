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

---

## 🛠️ Setup and Installation

### Prerequisites
- Java 17 or higher
- Maven 3.8+
- PostgreSQL 15+ (or use Docker)
- Node.js 18+ (for frontend)
- Docker & Docker Compose (recommended)

### Local Development Setup

#### Using Docker (Recommended)

1. **Clone the repository**
   ```bash
   git clone https://github.com/bos-com/GreenCode.git
   cd GreenCode
   ```

2. **Configure environment variables**
   ```bash
   cp env.example .env
   # Edit .env with your database credentials
   ```

3. **Start the application with Docker**
   ```bash
   docker-compose up -d
   ```

4. **Access the application**
   - Backend API: http://localhost:8080
   - Swagger Docs: http://localhost:8080/swagger-ui.html

#### Local Setup (Without Docker)

1. **Clone and navigate to the project**
   ```bash
   git clone https://github.com/bos-com/GreenCode.git
   cd GreenCode
   ```

2. **Configure database**
   - Create a PostgreSQL database named `greencode`
   - Update `src/main/resources/application.yml` with your database credentials

3. **Build and run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the API**
   - API running at: http://localhost:8080
   - Swagger documentation: http://localhost:8080/swagger-ui.html

### Building for Production

```bash
mvn clean package
java -jar target/greencode-*.jar
```

---

## 📚 Additional Documentation

- [Architecture Documentation](docs/architecture/README.md)
- [API Documentation](docs/api/README.md)
- [Deployment Guide](docs/deployment/README.md)

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'feat: add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
