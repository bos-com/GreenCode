# GreenCode API

A robust Java backend application built with Spring Boot for the GreenCode project - an innovative platform focused on sustainable development and environmental initiatives.
GreenCode is a full-stack system designed to support environmental, sustainability, and community-impact projects under Bos-Com.  
It consists of a **Spring Boot backend API** and a **React frontend** (new addition), with future integrations planned.

## 🎯 Project Overview
---

GreenCode is a comprehensive backend system designed to support environmental sustainability projects, green technology initiatives, and eco-friendly business operations. The platform provides robust APIs for managing environmental data, user authentication, and sustainable development metrics.
## 🚀 Features

## 🚀 Technology Stack
### Backend (Spring Boot)
- RESTful API
- JWT/OAuth authentication
- PostgreSQL database support
- Centralised configuration (`config/`, `.env`)
- Dockerized for easy deployment
- Swagger/OpenAPI documentation

- **Java 17** - Modern Java with latest features
- **Spring Boot 3.2.0** - Rapid application development framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Data persistence layer
- **H2 Database** - In-memory database for development
- **PostgreSQL** - Production database support
- **Maven** - Dependency management and build tool
- **JWT** - JSON Web Token authentication
- **Swagger/OpenAPI** - API documentation
- **Spring Actuator** - Application monitoring and metrics
### Frontend (React)
- Modern React (Create React App)
- React Router for navigation
- Axios for API communication
- Authentication UI (login, password reset flow)
- Responsive UI with Tailwind CSS (recommended)
- Ready to connect to backend reset API

## 📋 Prerequisites
---

- Java 17 or higher
- Maven 3.6 or higher
- PostgreSQL (for production)

## 🛠️ Quick Start

### 1. Clone and Setup
```bash
git clone https://github.com/bos-com/GreenCode.git
cd GreenCode
```

### 2. Build and Run
```bash
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 🏗️ Project Structure

```
## 📁 Project Structure
GreenCode/
├── pom.xml                           # Maven configuration
├── Dockerfile                        # Containerization
├── docker-compose.yml                # Multi-service setup
├── .env.example                      # Environment variables template
├── .gitignore                        # Git ignore rules
├── README.md                         # This file
├── docs/                             # Project documentation
│   ├── api/                          # API documentation
│   ├── deployment/                   # Deployment guides
│   └── architecture/                 # System architecture
├── scripts/                          # Utility scripts
│   ├── setup.sh                      # Development setup
│   ├── deploy.sh                     # Deployment script
│   └── backup.sh                     # Database backup
├── config/                           # External configuration
│   ├── nginx/                        # Nginx configuration
│   └── docker/                       # Docker configurations
├── src/
│   ├── main/
│   │   ├── java/com/greencode/
│   │   │   ├── GreenCodeApplication.java    # Main application
│   │   │   ├── config/                      # Configuration classes
│   │   │   │   ├── SecurityConfig.java      # Security setup
│   │   │   │   ├── DatabaseConfig.java      # Database configuration
│   │   │   │   ├── SwaggerConfig.java       # API documentation
│   │   │   │   └── CorsConfig.java          # CORS settings
│   │   │   ├── controller/                  # REST controllers
│   │   │   │   ├── UserController.java      # User management
│   │   │   │   ├── AuthController.java      # Authentication
│   │   │   │   └── HealthController.java    # Health checks
│   │   │   ├── dto/                         # Data Transfer Objects
│   │   │   │   ├── UserDto.java             # User DTO
│   │   │   │   ├── AuthDto.java             # Authentication DTO
│   │   │   │   └── ResponseDto.java         # Common responses
│   │   │   ├── entity/                      # JPA entities
│   │   │   │   ├── BaseEntity.java          # Base entity
│   │   │   │   ├── User.java                # User entity
│   │   │   │   └── Project.java             # Project entity
│   │   │   ├── exception/                   # Exception handling
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   ├── CustomException.java     # Custom exceptions
│   │   │   │   └── ErrorCode.java           # Error codes
│   │   │   ├── repository/                  # Data access layer
│   │   │   │   ├── UserRepository.java      # User repository
│   │   │   │   └── ProjectRepository.java   # Project repository
│   │   │   ├── service/                     # Business logic
│   │   │   │   ├── UserService.java         # User service
│   │   │   │   ├── AuthService.java         # Authentication service
│   │   │   │   └── ProjectService.java      # Project service
│   │   │   ├── util/                        # Utility classes
│   │   │   │   ├── JwtUtil.java             # JWT utilities
│   │   │   │   ├── ValidationUtil.java      # Validation helpers
│   │   │   │   └── DateUtil.java            # Date utilities
│   │   │   └── constant/                    # Constants
│   │   │       ├── AppConstants.java        # Application constants
│   │   │       └── SecurityConstants.java   # Security constants
│   │   └── resources/
│   │       ├── application.yml              # Main configuration
│   │       ├── application-dev.yml          # Development config
│   │       ├── application-prod.yml         # Production config
│   │       ├── db/                          # Database scripts
│   │       │   ├── schema.sql               # Database schema
│   │       │   └── data.sql                 # Initial data
│   │       └── static/                      # Static resources
│   └── test/
│       ├── java/com/greencode/
│       │   ├── GreenCodeApplicationTests.java
│       │   ├── controller/                  # Controller tests
│       │   ├── service/                     # Service tests
│       │   └── repository/                  # Repository tests
│       └── resources/
│           └── application-test.yml         # Test configuration
├── logs/                                  # Application logs
├── data/                                  # Data storage
└── reports/                               # Generated reports
```

## 🔧 Configuration

### Environment Variables
Copy `.env.example` to `.env` and configure:
```bash
# Database
DB_HOST=localhost
DB_PORT=5432
DB_NAME=greencode
DB_USER=postgres
DB_PASSWORD=password

# JWT
JWT_SECRET=your-secret-key
JWT_EXPIRATION=86400000

# Server
SERVER_PORT=8080
SERVER_CONTEXT_PATH=/api
```

### Database Setup
```bash
# Development (H2 - automatic)
# Production (PostgreSQL)
psql -U postgres -c "CREATE DATABASE greencode;"
```

## 🌐 API Endpoints

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration
- `POST /api/auth/refresh` - Refresh token
- `POST /api/auth/logout` - User logout

### User Management
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

### Project Management
- `GET /api/projects` - Get all projects
- `GET /api/projects/{id}` - Get project by ID
- `POST /api/projects` - Create new project
- `PUT /api/projects/{id}` - Update project
- `DELETE /api/projects/{id}` - Delete project

### Health & Monitoring
- `GET /api/health` - Application health
- `GET /api/metrics` - Application metrics
- `GET /api/info` - Application information

## 🔐 Security Features

- JWT-based authentication
- Role-based access control (USER, ADMIN, MODERATOR)
- Password encryption with BCrypt
- CORS configuration for frontend integration
- Input validation and sanitization
- Rate limiting protection

## 🗄️ Database Schema

### Core Tables
- **users** - User accounts and profiles
- **projects** - Environmental projects
- **roles** - User roles and permissions
- **audit_logs** - System activity tracking

### Key Features
- Soft delete support
- Audit trail for all changes
- Optimized indexes for performance
- Data validation constraints

## 🧪 Testing

```bash
# Run all tests
mvn test

# Run with coverage
mvn jacoco:report

# Run specific test
mvn test -Dtest=UserServiceTest

# Integration tests
mvn verify
```

## 🚀 Deployment

### Docker Compose
```bash
# Start all services
docker-compose up -d

# View logs
docker-compose logs -f

# Stop services
docker-compose down
```

### Production Deployment
```bash
# Build production JAR
mvn clean package -Pprod

# Run with production profile
java -jar -Dspring.profiles.active=prod target/greencode-backend-1.0.0.jar
```

## 📊 Monitoring

- **Health Checks**: `/api/health`
- **Metrics**: Prometheus format available
- **Logging**: Structured logging with logback
- **Tracing**: Request tracing for debugging

## 🔄 Development Workflow

1. **Feature Development**
   - Create feature branch: `git checkout -b feature/new-feature`
   - Implement changes with tests
   - Submit pull request

2. **Code Quality**
   - Run tests: `mvn test`
   - Check style: `mvn checkstyle:check`
   - Security scan: `mvn dependency:check`

3. **Deployment**
   - Development: Automatic deployment on push to dev branch
   - Production: Manual deployment with approval process

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes with tests
4. Ensure all tests pass
5. Submit a pull request

### Code Standards
- Follow Java naming conventions
- Add Javadoc for public methods
- Include unit tests for new features
- Use meaningful commit messages

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
├── src/                 # Spring Boot source code
├── config/              # external configuration & scripts
├── docs/                # architecture, API docs
├── greencode-frontend/  # React frontend (new)
├── pom.xml              # Maven build file
└── docker-compose.yml   # Docker orchestration

