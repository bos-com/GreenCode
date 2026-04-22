# GreenCode

GreenCode is a comprehensive full-stack system designed to support environmental, sustainability, and community-impact projects under Bos-Com. It consists of a **Spring Boot backend API** and a **React frontend**, with future integrations planned for enhanced functionality.

## 🌱 About GreenCode

GreenCode empowers sustainable agriculture through ICT, innovation, and digital solutions for smarter farming. The platform provides tools and resources for:

- **Environmental Monitoring**: Track and analyze environmental data
- **Sustainability Management**: Manage and optimize sustainable practices
- **Community Impact**: Measure and report community engagement
- **Agricultural Innovation**: Support smart farming initiatives
- **Data Analytics**: Comprehensive data analysis and reporting

## 🚀 Features

### Backend (Spring Boot)

- **RESTful API**: Comprehensive API endpoints for all operations
- **JWT/OAuth Authentication**: Secure authentication and authorization
- **PostgreSQL Database**: Robust database support with optimized schemas
- **Centralized Configuration**: External configuration via `config/` and `.env` files
- **Docker Support**: Containerized deployment for easy setup and scaling
- **Swagger/OpenAPI Documentation**: Interactive API documentation
- **Health Check Endpoints**: System monitoring and diagnostics
- **CORS Configuration**: Cross-origin resource sharing support
- **Validation**: Input validation and error handling
- **Logging**: Comprehensive logging for debugging and monitoring

### Frontend (React)

- **Modern React**: Built with Create React App for optimal performance
- **React Router**: Client-side routing for seamless navigation
- **Axios Integration**: HTTP client for API communication
- **Authentication UI**: Complete login and password reset flows
- **Responsive Design**: Mobile-first responsive UI with Tailwind CSS
- **State Management**: Efficient state management with React hooks
- **Error Handling**: Comprehensive error handling and user feedback
- **Loading States**: Optimized loading states for better UX

## 📁 Project Structure

```
GreenCode/
├── src/                      # Spring Boot source code
│   ├── main/
│   │   ├── java/
│   │   │   └── com/greencode/
│   │   │       ├── controller/    # REST controllers
│   │   │       ├── service/       # Business logic
│   │   │       ├── repository/   # Data access layer
│   │   │       ├── model/         # Entity models
│   │   │       ├── dto/           # Data transfer objects
│   │   │       ├── config/        # Spring configuration
│   │   │       └── security/      # Security configuration
│   │   └── resources/
│   │       ├── application.yml   # Application configuration
│   │       └── static/           # Static resources
│   └── test/                    # Test files
├── config/                     # External configuration & scripts
│   ├── application.yml         # Production configuration
│   └── scripts/                # Deployment and utility scripts
├── docs/                       # Architecture and API documentation
│   ├── api/                    # API documentation
│   ├── architecture/           # System architecture docs
│   └── deployment/             # Deployment guides
├── greencode-frontend/         # React frontend application
│   ├── public/                 # Public assets
│   ├── src/
│   │   ├── components/         # React components
│   │   ├── pages/              # Page components
│   │   ├── services/           # API services
│   │   ├── utils/              # Utility functions
│   │   ├── contexts/           # React contexts
│   │   └── App.js              # Main app component
│   └── package.json            # Frontend dependencies
├── pom.xml                     # Maven build file
├── docker-compose.yml          # Docker orchestration
├── Dockerfile                  # Docker image configuration
├── .env.example               # Environment variables template
└── README.md                   # This file
```

## 🛠️ Technology Stack

### Backend

- **Java 17**: Programming language
- **Spring Boot 3.2.0**: Application framework
- **Spring Data JPA**: Database abstraction
- **Spring Security**: Authentication and authorization
- **Spring Validation**: Input validation
- **PostgreSQL**: Relational database
- **JWT (JSON Web Tokens)**: Token-based authentication
- **Swagger/OpenAPI**: API documentation
- **Maven**: Build and dependency management
- **Docker**: Containerization

### Frontend

- **React 18**: UI framework
- **React Router 6**: Routing
- **Axios**: HTTP client
- **Tailwind CSS**: Styling
- **Create React App**: Build tool

### DevOps

- **Docker**: Containerization
- **Docker Compose**: Multi-container orchestration
- **Git**: Version control
- **GitHub**: Code hosting and collaboration

## 🚀 Quick Start

### Prerequisites

Before you begin, ensure you have the following installed:

- **Java 17+**: Download from [oracle.com](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.6+**: Download from [maven.apache.org](https://maven.apache.org/download.cgi)
- **Node.js 16+**: Download from [nodejs.org](https://nodejs.org/)
- **PostgreSQL 15+**: Download from [postgresql.org](https://www.postgresql.org/download/)
- **Docker & Docker Compose**: Download from [docker.com](https://www.docker.com/products/docker-desktop)

### Option 1: Docker Setup (Recommended)

This is the **recommended** way to run GreenCode as it ensures consistency across all environments.

1. **Clone the repository**
   ```bash
   git clone https://github.com/bos-com/GreenCode.git
   cd GreenCode
   ```

2. **Set up environment variables**
   ```bash
   # Copy environment template
   cp env.example .env
   
   # Edit .env with your configuration
   nano .env  # or your preferred editor
   ```

3. **Build and start all services**
   ```bash
   # Build Docker images
   docker-compose build
   
   # Start all services (database, backend, frontend)
   docker-compose up -d
   ```

4. **Verify the installation**
   ```bash
   # Check service status
   docker-compose ps
   
   # View logs
   docker-compose logs -f
   ```

5. **Access the application**
   - **Backend API**: http://localhost:8080
   - **Frontend**: http://localhost:3000
   - **API Documentation**: http://localhost:8080/swagger-ui.html
   - **Health Check**: http://localhost:8080/actuator/health

### Option 2: Local Development Setup

If you prefer to run the application locally without Docker:

#### Backend Setup

1. **Navigate to the project directory**
   ```bash
   cd GreenCode
   ```

2. **Set up PostgreSQL database**
   ```bash
   # Create database
   createdb greencode
   
   # Or using psql
   psql -U postgres
   CREATE DATABASE greencode;
   \q
   ```

3. **Configure environment variables**
   ```bash
   cp env.example .env
   # Edit .env with your database credentials
   ```

4. **Build the project**
   ```bash
   mvn clean install
   ```

5. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

The backend will start at `http://localhost:8080`

#### Frontend Setup

1. **Navigate to the frontend directory**
   ```bash
   cd greencode-frontend
   ```

2. **Install dependencies**
   ```bash
   npm install
   ```

3. **Start the development server**
   ```bash
   npm start
   ```

The frontend will start at `http://localhost:3000`

## 📊 API Documentation

The API is fully documented using Swagger/OpenAPI:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs
- **OpenAPI YAML**: http://localhost:8080/v3/api-docs.yaml

### Key API Endpoints

- **Authentication**: `/api/v1/auth/*`
- **Users**: `/api/v1/users/*`
- **Projects**: `/api/v1/projects/*`
- **Environmental Data**: `/api/v1/environment/*`
- **Analytics**: `/api/v1/analytics/*`
- **Reports**: `/api/v1/reports/*`

## 🔧 Configuration

### Environment Variables

Configure the following environment variables in `.env`:

```bash
# Server Configuration
SERVER_PORT=8080
SPRING_PROFILES_ACTIVE=dev

# Database Configuration
DB_HOST=localhost
DB_PORT=5432
DB_NAME=greencode
DB_USER=greencode_user
DB_PASSWORD=your-secure-password

# JWT Configuration
JWT_SECRET=your-jwt-secret-key
JWT_EXPIRATION=86400000  # 24 hours in milliseconds

# CORS Configuration
CORS_ALLOWED_ORIGINS=http://localhost:3000,http://localhost:8080

# Frontend Configuration
FRONTEND_URL=http://localhost:3000
```

### Application Properties

The main configuration is in `src/main/resources/application.yml`:

```yaml
spring:
  application:
    name: greencode
  datasource:
    url: jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}
    username: ${DB_USER}
    password: ${DB_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
  security:
    jwt:
      secret: ${JWT_SECRET}
      expiration: ${JWT_EXPIRATION}

server:
  port: ${SERVER_PORT:8080}
```

## 🧪 Testing

### Backend Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=UserServiceTest

# Run with coverage
mvn test jacoco:report
```

### Frontend Tests

```bash
# Run all tests
npm test

# Run tests in watch mode
npm test -- --watch

# Run tests with coverage
npm test -- --coverage
```

## 📈 Development

### Backend Development

1. **Create a new feature branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. **Make your changes**

3. **Run tests**
   ```bash
   mvn test
   ```

4. **Build the project**
   ```bash
   mvn clean install
   ```

5. **Commit your changes**
   ```bash
   git add .
   git commit -m "feat: add your feature description"
   ```

### Frontend Development

1. **Navigate to frontend directory**
   ```bash
   cd greencode-frontend
   ```

2. **Start development server**
   ```bash
   npm start
   ```

3. **Make your changes**

4. **Run tests**
   ```bash
   npm test
   ```

5. **Build for production**
   ```bash
   npm run build
   ```

## 🚢 Deployment

### Docker Deployment

```bash
# Build production images
docker-compose -f docker-compose.prod.yml build

# Start production services
docker-compose -f docker-compose.prod.yml up -d

# View logs
docker-compose -f docker-compose.prod.yml logs -f
```

### Manual Deployment

#### Backend

```bash
# Build JAR file
mvn clean package -DskipTests

# Run JAR file
java -jar target/greencode-backend-1.0.0.jar
```

#### Frontend

```bash
# Build for production
npm run build

# Deploy build/ directory to your web server
```

## 🔒 Security

- **Authentication**: JWT-based authentication
- **Authorization**: Role-based access control
- **Password Encryption**: BCrypt encryption
- **CORS**: Configured CORS policies
- **Input Validation**: Comprehensive input validation
- **SQL Injection Prevention**: Parameterized queries
- **XSS Protection**: Content Security Policy

## 📚 Documentation

- **API Documentation**: Available at `/swagger-ui.html`
- **Architecture Docs**: Located in `docs/architecture/`
- **Deployment Guides**: Located in `docs/deployment/`
- **API Reference**: Located in `docs/api/`

## 🤝 Contributing

We welcome contributions to GreenCode! Please follow these guidelines:

1. **Fork the repository**
2. **Create a feature branch** (`git checkout -b feature/AmazingFeature`)
3. **Commit your changes** (`git commit -m 'Add some AmazingFeature'`)
4. **Push to the branch** (`git push origin feature/AmazingFeature`)
5. **Open a Pull Request**

### Development Guidelines

- Follow Java coding conventions
- Write meaningful commit messages
- Add tests for new features
- Update documentation as needed
- Ensure all tests pass before submitting

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Spring Boot community for the excellent framework
- React community for the amazing UI library
- PostgreSQL team for the robust database
- All contributors who have helped improve GreenCode

## 📞 Support

For support, please:

- Open an issue on [GitHub Issues](https://github.com/bos-com/GreenCode/issues)
- Join our community discussions
- Contact the development team

## 🗺️ Roadmap

### Phase 1 (Current)
- ✅ Core backend API
- ✅ Basic frontend
- ✅ Authentication system
- ✅ Database integration

### Phase 2 (Next)
- 🔄 Advanced analytics dashboard
- 🔄 Mobile application
- 🔄 Real-time data updates
- 🔄 Enhanced reporting features

### Phase 3 (Future)
- 📋 Machine learning integration
- 📋 IoT device integration
- 📋 Advanced visualization
- 📋 Multi-language support

---

**GreenCode** - Empowering sustainable agriculture through technology and innovation.

🌱 **Made with ❤️ by the Bos-Com Team**
