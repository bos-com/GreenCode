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

## 🚀 Quick Start

### Prerequisites

- **Java 17** or higher
- **Maven 3.8** or higher
- **Node.js 16** or higher (for frontend)
- **PostgreSQL 13** or higher
- **Docker & Docker Compose** (optional, recommended)

### Option 1: Docker Setup (Recommended)

1. **Clone the repository**
   ```bash
   git clone https://github.com/MarlonPiusMulamba/GreenCode.git
   cd GreenCode
   ```

2. **Start all services with Docker Compose**
   ```bash
   # Build and start all services
   docker-compose up -d
   
   # Check service status
   docker-compose ps
   ```

3. **Access the applications**
   - **Backend API**: http://localhost:8080
   - **Frontend**: http://localhost:3000
   - **API Docs**: http://localhost:8080/swagger-ui.html

### Option 2: Local Development Setup

#### Backend Setup (Spring Boot)

1. **Navigate to project root**
   ```bash
   cd GreenCode
   ```

2. **Set up database**
   ```bash
   # Create PostgreSQL database
   createdb greencode
   
   # Or use psql
   psql -c "CREATE DATABASE greencode;"
   ```

3. **Configure environment variables**
   ```bash
   # Copy environment template
   cp config/.env.example config/.env
   
   # Edit config/.env with your settings
   # Database URL, JWT secret, etc.
   ```

4. **Build and run the backend**
   ```bash
   # Using Maven
   mvn clean install
   mvn spring-boot:run
   
   # Or using the Maven wrapper
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

5. **Run database migrations**
   ```bash
   # Flyway migrations run automatically on startup
   # Or run manually:
   mvn flyway:migrate
   ```

#### Frontend Setup (React)

1. **Navigate to frontend directory**
   ```bash
   cd greencode-frontend
   ```

2. **Install dependencies**
   ```bash
   npm install
   # OR
   yarn install
   ```

3. **Set up environment variables**
   ```bash
   # Create environment file
   cp .env.example .env.local
   
   # Edit .env.local with backend URL
   REACT_APP_API_URL=http://localhost:8080/api
   ```

4. **Start the development server**
   ```bash
   npm start
   # OR
   yarn start
   ```

5. **Access the frontend**
   Open http://localhost:3000 in your browser

## 📚 API Documentation

### Swagger/OpenAPI Documentation

Once the backend is running, visit:
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

### Key API Endpoints

| Category | Base Path | Description |
|----------|-----------|-------------|
| **Authentication** | `/api/auth` | Login, register, refresh tokens |
| **Users** | `/api/users` | User management and profiles |
| **Projects** | `/api/projects` | Environmental project management |
| **Sustainability** | `/api/sustainability` | Sustainability metrics and tracking |
| **Analytics** | `/api/analytics` | Data analytics and reporting |
| **Files** | `/api/files` | File upload and management |

### Authentication

The API uses JWT-based authentication:

```bash
# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"your@email.com","password":"password"}'

# Use returned token in subsequent requests
curl -X GET http://localhost:8080/api/projects \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## 🔧 Configuration

### Environment Variables

#### Backend Configuration

Create `config/.env`:

```bash
# Database Configuration
DB_URL=jdbc:postgresql://localhost:5432/greencode
DB_USERNAME=greencode_user
DB_PASSWORD=your_password

# JWT Configuration
JWT_SECRET=your-super-secret-jwt-key
JWT_EXPIRATION=86400000

# Server Configuration
SERVER_PORT=8080
SERVER_HOST=localhost

# File Upload
UPLOAD_DIR=./uploads
MAX_FILE_SIZE=10485760

# Email Configuration (optional)
EMAIL_HOST=smtp.gmail.com
EMAIL_PORT=587
EMAIL_USERNAME=your-email@gmail.com
EMAIL_PASSWORD=your-app-password
```

#### Frontend Configuration

Create `greencode-frontend/.env.local`:

```bash
# API Configuration
REACT_APP_API_URL=http://localhost:8080/api
REACT_APP_API_TIMEOUT=10000

# Application Configuration
REACT_APP_NAME=GreenCode
REACT_APP_VERSION=1.0.0

# Feature Flags
REACT_APP_ENABLE_ANALYTICS=true
REACT_APP_ENABLE_UPLOADS=true
```

## 🧪 Testing

### Backend Tests

```bash
# Run all tests
mvn test

# Run tests with coverage
mvn jacoco:report

# Run specific test class
mvn test -Dtest=ProjectServiceTest

# Run integration tests
mvn test -Dtest=**/*IntegrationTest
```

### Frontend Tests

```bash
# Navigate to frontend directory
cd greencode-frontend

# Run all tests
npm test

# Run tests with coverage
npm run test:coverage

# Run tests in watch mode
npm run test:watch

# Run E2E tests
npm run test:e2e
```

## 🚀 Deployment

### Docker Production

1. **Build production images**
   ```bash
   docker-compose -f docker-compose.prod.yml build
   ```

2. **Start production services**
   ```bash
   docker-compose -f docker-compose.prod.yml up -d
   ```

3. **Health checks**
   ```bash
   # Check backend health
   curl http://localhost:8080/actuator/health
   
   # Check frontend
   curl http://localhost:3000
   ```

### Manual Deployment

#### Backend Deployment

1. **Build JAR file**
   ```bash
   mvn clean package -DskipTests
   ```

2. **Run with production profile**
   ```bash
   java -jar target/greencode-1.0.0.jar --spring.profiles.active=prod
   ```

#### Frontend Deployment

1. **Build for production**
   ```bash
   cd greencode-frontend
   npm run build
   ```

2. **Deploy build artifacts**
   ```bash
   # Copy build directory to your web server
   cp -r build/* /var/www/html/
   ```

## 🐛 Troubleshooting

### Common Issues

**1. Database connection failed**
```bash
# Check PostgreSQL status
pg_isready

# Check database exists
psql -l | grep greencode

# Test connection
psql -h localhost -U greencode_user -d greencode
```

**2. Port conflicts**
```bash
# Check what's using port 8080
netstat -tulpn | grep :8080

# Kill process
kill -9 <PID>

# Or use different port
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

**3. Frontend can't connect to backend**
```bash
# Check backend is running
curl http://localhost:8080/actuator/health

# Check CORS configuration
# Ensure backend allows requests from http://localhost:3000
```

**4. Maven build failures**
```bash
# Clean Maven cache
mvn clean

# Force update dependencies
mvn clean install -U

# Check Java version
java -version
```

**5. Node.js issues**
```bash
# Clear npm cache
npm cache clean --force

# Delete node_modules and reinstall
rm -rf node_modules package-lock.json
npm install

# Check Node version
node --version  # Should be 16+
```

### Docker Issues

**1. Container won't start**
```bash
# Check logs
docker-compose logs backend
docker-compose logs frontend

# Rebuild containers
docker-compose down
docker-compose build --no-cache
docker-compose up -d
```

**2. Database connection in Docker**
```bash
# Check database container
docker-compose exec db psql -U greencode_user -d greencode

# Restart database
docker-compose restart db
```

## 📁 Project Structure (Expanded)

```
GreenCode/
├── src/                           # Spring Boot source code
│   ├── main/
│   │   ├── java/com/greencode/
│   │   │   ├── config/           # Security, database, CORS config
│   │   │   ├── controller/       # REST API controllers
│   │   │   ├── service/          # Business logic services
│   │   │   ├── repository/       # JPA repositories
│   │   │   ├── model/           # JPA entities
│   │   │   ├── dto/             # Data transfer objects
│   │   │   └── util/            # Utility classes
│   │   └── resources/
│   │       ├── application.yml   # Spring configuration
│   │       └── db/migration/     # Flyway migrations
│   └── test/                     # Test classes
├── config/                       # External configuration
│   ├── .env.example             # Environment template
│   └── docker/                  # Docker configurations
├── docs/                         # Documentation
│   ├── api/                     # API documentation
│   └── deployment/              # Deployment guides
├── greencode-frontend/           # React frontend
│   ├── public/                  # Static assets
│   ├── src/
│   │   ├── components/          # React components
│   │   ├── pages/              # Page components
│   │   ├── services/           # API services
│   │   ├── utils/              # Utility functions
│   │   └── styles/              # CSS/SCSS files
│   ├── package.json             # Node.js dependencies
│   └── .env.example            # Frontend env template
├── target/                      # Maven build output
├── pom.xml                      # Maven build file
├── docker-compose.yml           # Development Docker setup
├── docker-compose.prod.yml      # Production Docker setup
└── README.md                   # This file
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Make your changes
4. Add tests for new functionality
5. Ensure all tests pass
6. Commit your changes (`git commit -m 'feat: add amazing feature'`)
7. Push to the branch (`git push origin feature/amazing-feature`)
8. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


