# GreenCode

GreenCode is an API core system designed to support esm_greencode.
It consists of a **Spring Boot backend API** with a React frontend.

---

## 🚀 Features

### Backend (Spring Boot)
- RESTful API endpoints
- JWT/OAuth authentication
- PostgreSQL database support
- Centralized configuration (`config/`, `.env`)
- Dockerized for easy deployment
- Swagger/OpenAPI documentation

### Frontend (React)
- Modern user interface
- Responsive design
- Integration with GreenCode API
- State management

---

## 📁 Project Structure
```
GreenCode/
├── src/                 # Spring Boot source code
├── config/              # External configuration & scripts
├── docs/                # Architecture, API documentation
├── greencode-frontend/  # React frontend application
├── pom.xml              # Maven build configuration
└── docker-compose.yml   # Docker orchestration file
```

---

## 🛠️ Technology Stack

### Backend
- **Language:** Java 17
- **Framework:** Spring Boot 3.x
- **Database:** PostgreSQL with Spring Data JPA
- **Security:** Spring Security with JWT/OAuth
- **API Documentation:** Swagger/OpenAPI 3.0
- **Build Tool:** Maven

### Frontend
- **Library:** React 18
- **Styling:** CSS3 with responsive design
- **HTTP Client:** Axios
- **Routing:** React Router v6
- **State Management:** React Context API

### DevOps & Infrastructure
- **Containerization:** Docker
- **Orchestration:** Docker Compose
- **Version Control:** Git
- **CI/CD:** GitHub Actions (planned)

---

## ⚙️ Setup and Installation

### Prerequisites
- Java 17 or higher (OpenJDK or Oracle JDK)
- Maven 3.8+
- Node.js 18+ and npm (for frontend)
- Docker and Docker Compose v2
- PostgreSQL 13+

### Backend Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Bienvenu-crypto/GreenCode.git
   cd GreenCode
   ```

2. **Configure environment variables:**
   ```bash
   cp env.example .env
   ```
   Edit `.env` with your configuration:
   ```env
   # Database
   SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/greencode
   SPRING_DATASOURCE_USERNAME=your_username
   SPRING_DATASOURCE_PASSWORD=your_password
   
   # JWT Security
   JWT_SECRET=your_strong_secret_key_here
   JWT_EXPIRATION_MS=86400000
   
   # Server
   SERVER_PORT=8080
   ```

3. **Build the backend:**
   ```bash
   ./mvnw clean install
   ```

4. **Run the backend:**
   ```bash
   ./mvnw spring-boot:run
   ```
   The API will be available at `http://localhost:8080`.

### Frontend Setup

1. **Navigate to the frontend directory:**
   ```bash
   cd greencode-frontend
   ```

2. **Install dependencies:**
   ```bash
   npm install
   ```

3. **Configure API connection:**
   Create `.env` in the frontend directory:
   ```env
   REACT_APP_API_URL=http://localhost:8080/api
   ```

4. **Start the development server:**
   ```bash
   npm start
   ```
   The frontend will be available at `http://localhost:3000`.

### Docker Setup (Recommended for Development)

1. **Ensure Docker and Docker Compose are installed.**

2. **Build and start all services:**
   ```bash
   docker-compose up --build
   ```

3. **Access the applications:**
   - Backend API: `http://localhost:8080`
   - Frontend: `http://localhost:3000`
   - API Documentation: `http://localhost:8080/swagger-ui.html`

4. **To stop and clean up:**
   ```bash
   docker-compose down
   ```

---

## 📚 API Documentation

Once the backend is running, access the interactive API documentation:

- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **OpenAPI JSON:** http://localhost:8080/v3/api-docs
- **OpenAPI YAML:** http://localhost:8080/v3/api-docs/yaml

---

## 🧪 Testing

### Backend Tests
```bash
# Run all tests
./mvnw test

# Run only unit tests
./mvnw test -DskipITs

# Run only integration tests
./mvnw verify -Dskip.unit.tests
```

### Frontend Tests
```bash
# Run tests
npm test

# Run tests in watch mode
npm test -- --watch

# Run tests with coverage
npm test -- --coverage
```

---

## 🤝 Contributing

We welcome contributions from the community! Please follow these guidelines:

1. **Fork the repository** on GitHub
2. **Create a feature branch** from `main`:
   ```bash
   git checkout -b feature/amazing-feature
   ```
3. **Make your changes** following our coding standards
4. **Add tests** for new functionality
5. **Ensure all tests pass**
6. **Commit your changes** with descriptive messages
7. **Push to your fork** and submit a pull request

### Pull Request Process
1. Update README.md with details of changes if applicable
2. The PR will be reviewed by maintainers
3. Address any feedback from reviewers
4. Once approved, your PR will be merged

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for detailed guidelines.

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 📞 Contact

For questions, support, or collaboration inquiries:
- Open an issue on this repository
- Contact the maintainers through GitHub Discussions
- Email: oss@bos-community.org (if applicable)

---

## 🙏 Acknowledgments

- Bugema Open Source Community (BOSC) for project stewardship
- All contributors who have helped shape GreenCode
- The open-source community for tools and inspiration
