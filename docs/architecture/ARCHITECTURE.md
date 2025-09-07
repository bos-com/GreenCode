# GreenCode Backend System Architecture

## Table of Contents
1. [System Overview](#system-overview)
2. [Architecture Patterns](#architecture-patterns)
3. [Component Architecture](#component-architecture)
4. [Data Architecture](#data-architecture)
5. [Security Architecture](#security-architecture)
6. [Infrastructure Architecture](#infrastructure-architecture)
7. [Communication Patterns](#communication-patterns)
8. [Deployment Architecture](#deployment-architecture)
9. [Monitoring & Observability](#monitoring--observability)
10. [Scalability & Performance](#scalability--performance)
11. [Development Guidelines](#development-guidelines)

## System Overview

GreenCode is an environmental project management platform built with a modern, scalable backend architecture. The system follows Domain-Driven Design (DDD) principles and implements a layered architecture pattern to ensure maintainability, testability, and scalability.

### Core Business Domain
- **Environmental Project Management**: Track and manage sustainability projects
- **User Management**: Authentication, authorization, and user profiles
- **Project Lifecycle**: From planning to completion with impact tracking
- **Sustainability Metrics**: Environmental impact scoring and reporting

### Technology Stack
- **Framework**: Spring Boot 3.2.0
- **Language**: Java 17
- **Database**: PostgreSQL (production), H2 (development)
- **Cache**: Redis
- **Security**: Spring Security with JWT
- **Documentation**: OpenAPI 3.0 (Swagger)
- **Monitoring**: Prometheus + Grafana
- **Containerization**: Docker + Docker Compose
- **Reverse Proxy**: Nginx

## Architecture Patterns

### 1. Layered Architecture
The system implements a traditional layered architecture with clear separation of concerns:

```
┌─────────────────────────────────────────────────────────────┐
│                    Presentation Layer                       │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐ │
│  │   REST API  │  │  Swagger UI │  │   Health Endpoints  │ │
│  │ Controllers │  │  OpenAPI    │  │    Actuator         │ │
│  └─────────────┘  └─────────────┘  └─────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────────────────┐
│                     Business Layer                          │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐ │
│  │ User Service│  │Auth Service │  │  Project Service    │ │
│  │ DTOs        │  │JWT Utils    │  │  Validation         │ │
│  └─────────────┘  └─────────────┘  └─────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────────────────┐
│                    Data Access Layer                        │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐ │
│  │User Repo    │  │Project Repo │  │   Audit Repository  │ │
│  │JPA Entities │  │Custom Queries│  │   Cache Layer       │ │
│  └─────────────┘  └─────────────┘  └─────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────────────────┐
│                     Data Layer                              │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐ │
│  │ PostgreSQL  │  │    Redis    │  │    File Storage     │ │
│  │ (Primary)   │  │   (Cache)   │  │   (Documents)       │ │
│  └─────────────┘  └─────────────┘  └─────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
```

### 2. Domain-Driven Design (DDD)
The system is organized around business domains:

- **User Domain**: User management, authentication, authorization
- **Project Domain**: Environmental project lifecycle management
- **Audit Domain**: System activity tracking and compliance

### 3. Repository Pattern
Data access is abstracted through repository interfaces, providing:
- Clean separation between business logic and data access
- Testability through mock implementations
- Database-agnostic business logic

## Component Architecture

### Core Components

#### 1. Controllers (Presentation Layer)
- **UserController**: User CRUD operations and profile management
- **ProjectController**: Project lifecycle management
- **AuthController**: Authentication and authorization endpoints
- **HealthController**: System health and monitoring endpoints

**Responsibilities:**
- HTTP request/response handling
- Input validation and sanitization
- DTO transformation
- Error handling and status codes

#### 2. Services (Business Layer)
- **UserService**: User business logic and validation
- **ProjectService**: Project management business rules
- **AuthService**: Authentication and JWT token management
- **NotificationService**: System notifications and alerts

**Responsibilities:**
- Business logic implementation
- Transaction management
- Data validation
- Integration with external services

#### 3. Repositories (Data Access Layer)
- **UserRepository**: User data persistence
- **ProjectRepository**: Project data persistence
- **AuditRepository**: System audit trail

**Responsibilities:**
- Data persistence operations
- Custom query implementation
- Database optimization
- Cache integration

#### 4. Entities (Domain Layer)
- **User**: User domain model with roles and permissions
- **Project**: Environmental project domain model
- **BaseEntity**: Common audit fields and lifecycle management

**Responsibilities:**
- Domain model representation
- Business rule enforcement
- Data validation constraints
- Relationship mapping

### Configuration Components

#### 1. Security Configuration
- **SecurityConfig**: Spring Security configuration
- **JWT Utils**: Token generation and validation
- **Password Encoder**: BCrypt password hashing

#### 2. Application Configuration
- **Application Properties**: Environment-specific configuration
- **Database Configuration**: Connection pooling and transaction management
- **Cache Configuration**: Redis integration and caching strategies

## Data Architecture

### Database Design

#### Core Tables

**Users Table**
```sql
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(120) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    role VARCHAR(20) DEFAULT 'USER',
    is_enabled BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    is_active BOOLEAN DEFAULT true
);
```

**Projects Table**
```sql
CREATE TABLE projects (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    category VARCHAR(50) NOT NULL,
    status VARCHAR(20) DEFAULT 'PLANNED',
    start_date DATE,
    end_date DATE,
    budget DECIMAL(15,2),
    actual_cost DECIMAL(15,2),
    location VARCHAR(500),
    coordinates VARCHAR(100),
    impact_score INTEGER CHECK (impact_score >= 1 AND impact_score <= 10),
    sustainability_rating INTEGER CHECK (sustainability_rating >= 1 AND sustainability_rating <= 5),
    manager_id BIGINT REFERENCES users(id),
    team_size INTEGER,
    is_public BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    is_active BOOLEAN DEFAULT true
);
```

### Data Relationships
- **User ↔ Project**: One-to-Many (User can manage multiple projects)
- **Project ↔ AuditLog**: One-to-Many (Project has multiple audit entries)
- **User ↔ Role**: Many-to-Many (Future enhancement for complex role management)

### Caching Strategy
- **Redis Integration**: Session management and frequently accessed data
- **Cache Patterns**: 
  - Write-through for user sessions
  - Cache-aside for project data
  - TTL-based expiration for temporary data

## Security Architecture

### Authentication Flow
```
┌─────────────┐    ┌─────────────┐    ┌─────────────┐    ┌─────────────┐
│   Client    │───▶│   Login     │───▶│  Validate   │───▶│   Generate  │
│             │    │  Request    │    │ Credentials │    │    JWT      │
└─────────────┘    └─────────────┘    └─────────────┘    └─────────────┘
       │                                                         │
       │                                                         ▼
       │                                                ┌─────────────┐
       │                                                │   Return    │
       └────────────────────────────────────────────────│ JWT Token   │
                                                        └─────────────┘
```

### Authorization Model
- **Role-Based Access Control (RBAC)**:
  - USER: Basic project access
  - MODERATOR: Project management capabilities
  - ADMIN: Full system access

- **Resource-Level Permissions**:
  - Project ownership validation
  - Public/private project access control
  - Audit trail access restrictions

### Security Features
- **Password Security**: BCrypt hashing with salt
- **JWT Tokens**: Stateless authentication with configurable expiration
- **CORS Configuration**: Cross-origin request handling
- **Input Validation**: Comprehensive request validation
- **SQL Injection Prevention**: JPA/Hibernate parameterized queries

## Infrastructure Architecture

### Container Architecture
```
┌─────────────────────────────────────────────────────────────┐
│                    Docker Network                           │
│                  (greencode-network)                        │
└─────────────────────────────────────────────────────────────┘
┌─────────────┐  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐
│   Nginx     │  │GreenCode    │  │ PostgreSQL  │  │    Redis    │
│ (Port 80/443)│  │ Backend     │  │ (Port 5432) │  │ (Port 6379) │
│             │  │ (Port 8080) │  │             │  │             │
└─────────────┘  └─────────────┘  └─────────────┘  └─────────────┘
┌─────────────┐  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐
│  Prometheus │  │   Grafana   │  │   Adminer   │  │   Logs      │
│ (Port 9090) │  │ (Port 3000) │  │ (Port 8081) │  │  Volume     │
└─────────────┘  └─────────────┘  └─────────────┘  └─────────────┘
```

### Service Dependencies
- **GreenCode Backend** → PostgreSQL, Redis
- **Nginx** → GreenCode Backend
- **Grafana** → Prometheus
- **Adminer** → PostgreSQL

### Volume Management
- **PostgreSQL Data**: Persistent storage for database
- **Redis Data**: Cache persistence
- **Prometheus Data**: Metrics storage
- **Grafana Data**: Dashboard configurations
- **Application Logs**: Centralized logging

## Communication Patterns

### 1. REST API Communication
- **Synchronous Communication**: HTTP/HTTPS for client-server interaction
- **API Versioning**: URL-based versioning (`/api/v1/`)
- **Content Negotiation**: JSON request/response format
- **HTTP Status Codes**: Standard REST status codes

### 2. Internal Service Communication
- **Direct Method Calls**: Within the same application
- **Repository Pattern**: Data access abstraction
- **Service Layer**: Business logic encapsulation

### 3. External Integration Points
- **Database**: JDBC connection pooling
- **Cache**: Redis client integration
- **Monitoring**: Prometheus metrics collection
- **Logging**: Structured logging with correlation IDs

### 4. Future Messaging Patterns
- **Event-Driven Architecture**: For microservices communication
- **Message Queues**: Asynchronous processing
- **WebSockets**: Real-time notifications
- **GraphQL**: Flexible data querying

## Deployment Architecture

### Development Environment
- **Local Development**: Docker Compose with H2 database
- **Hot Reload**: Spring Boot DevTools integration
- **Debugging**: Remote debugging support
- **Testing**: Embedded test containers

### Production Environment
- **Container Orchestration**: Docker Swarm or Kubernetes ready
- **Load Balancing**: Nginx reverse proxy
- **Database**: PostgreSQL with connection pooling
- **Monitoring**: Prometheus + Grafana stack
- **Logging**: Centralized log aggregation

### CI/CD Pipeline
- **Build**: Maven-based build process
- **Testing**: Unit and integration tests
- **Containerization**: Multi-stage Docker builds
- **Deployment**: Blue-green deployment strategy

## Monitoring & Observability

### Metrics Collection
- **Application Metrics**: Spring Boot Actuator
- **JVM Metrics**: Memory, CPU, garbage collection
- **Database Metrics**: Connection pool, query performance
- **Custom Metrics**: Business-specific KPIs

### Health Checks
- **Liveness Probe**: Application startup verification
- **Readiness Probe**: Service availability check
- **Database Health**: Connection and query validation
- **Cache Health**: Redis connectivity check

### Logging Strategy
- **Structured Logging**: JSON format with correlation IDs
- **Log Levels**: DEBUG, INFO, WARN, ERROR
- **Log Aggregation**: Centralized log collection
- **Audit Trail**: User action tracking

### Alerting
- **Performance Alerts**: Response time thresholds
- **Error Rate Alerts**: Exception frequency monitoring
- **Resource Alerts**: Memory and CPU usage
- **Business Alerts**: Critical business metric thresholds

## Scalability & Performance

### Horizontal Scaling
- **Stateless Design**: JWT-based authentication
- **Load Balancing**: Nginx round-robin distribution
- **Database Scaling**: Read replicas and connection pooling
- **Cache Scaling**: Redis cluster support

### Performance Optimization
- **Database Indexing**: Optimized query performance
- **Connection Pooling**: HikariCP configuration
- **Caching Strategy**: Multi-level caching approach
- **Async Processing**: Non-blocking operations

### Resource Management
- **Memory Management**: JVM tuning and monitoring
- **CPU Optimization**: Thread pool configuration
- **I/O Optimization**: Database query optimization
- **Network Optimization**: Connection reuse and compression

## Development Guidelines

### Code Organization
- **Package Structure**: Domain-driven package organization
- **Naming Conventions**: Clear and descriptive naming
- **Documentation**: Comprehensive JavaDoc and README files
- **Code Reviews**: Mandatory peer review process

### Testing Strategy
- **Unit Tests**: Service and repository layer testing
- **Integration Tests**: API endpoint testing
- **Test Coverage**: Minimum 80% code coverage
- **Test Data**: Isolated test data management

### Security Guidelines
- **Input Validation**: Comprehensive request validation
- **Authentication**: Secure token management
- **Authorization**: Principle of least privilege
- **Data Protection**: Encryption at rest and in transit

### Performance Guidelines
- **Database Queries**: Optimized and indexed queries
- **Caching**: Strategic cache implementation
- **Resource Usage**: Efficient memory and CPU utilization
- **Monitoring**: Proactive performance monitoring

---

## Conclusion

This architecture provides a solid foundation for the GreenCode backend system, ensuring scalability, maintainability, and security. The modular design allows for future enhancements and microservices migration when needed. Regular architecture reviews and updates will ensure the system continues to meet evolving business requirements.

For implementation details and specific configurations, refer to the individual component documentation and the deployment guides in the `/docs` directory.
