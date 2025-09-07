# GreenCode System Architecture

This directory contains comprehensive system architecture documentation and diagrams for the GreenCode backend system.

## 📚 Documentation Structure

### Core Architecture Documents
- **[ARCHITECTURE.md](./ARCHITECTURE.md)** - Comprehensive system architecture overview
- **[COMMUNICATION_PATTERNS.md](./COMMUNICATION_PATTERNS.md)** - Detailed communication patterns and protocols

### Architecture Diagrams
- **[System Overview](./diagrams/system-overview.md)** - High-level system architecture and component interactions
- **[Application Architecture](./diagrams/application-architecture.md)** - Detailed application layer architecture
- **[Deployment Architecture](./diagrams/deployment-architecture.md)** - Container and deployment architecture
- **[Data Flow](./diagrams/data-flow.md)** - Data flow patterns and processing sequences

## 🏗️ System Overview

GreenCode is built using a modern, scalable backend architecture following Domain-Driven Design (DDD) principles and implementing a layered architecture pattern with clear separation of concerns.

### Technology Stack
- **Framework**: Spring Boot 3.2.0 with Java 17
- **Database**: PostgreSQL (production), H2 (development)
- **Cache**: Redis for session management and caching
- **Security**: Spring Security with JWT authentication
- **Documentation**: OpenAPI 3.0 (Swagger)
- **Monitoring**: Prometheus + Grafana stack
- **Containerization**: Docker + Docker Compose
- **Reverse Proxy**: Nginx

### Architecture Layers
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

## 🔐 Security Architecture

### Authentication & Authorization
- **JWT-based Authentication**: Stateless token-based authentication
- **Role-Based Access Control (RBAC)**: USER, MODERATOR, ADMIN roles
- **Password Security**: BCrypt hashing with salt
- **CORS Configuration**: Cross-origin request handling
- **Input Validation**: Comprehensive request validation

### Security Features
- **SQL Injection Prevention**: JPA/Hibernate parameterized queries
- **XSS Protection**: Input sanitization and output encoding
- **CSRF Protection**: Token-based CSRF protection
- **Rate Limiting**: API rate limiting (future enhancement)

## 🗄️ Database Design

### Core Entities
- **User**: Authentication, profile data, and role management
- **Project**: Environmental project lifecycle management
- **BaseEntity**: Common audit fields and lifecycle management
- **AuditLog**: System activity tracking and compliance

### Data Relationships
- **User ↔ Project**: One-to-Many (User can manage multiple projects)
- **Project ↔ AuditLog**: One-to-Many (Project has multiple audit entries)
- **User ↔ Role**: Many-to-Many (Future enhancement for complex role management)

### Database Features
- **Connection Pooling**: HikariCP for optimal performance
- **Transaction Management**: ACID compliance with proper isolation levels
- **Audit Trail**: Automatic tracking of entity changes
- **Soft Deletes**: Logical deletion with isActive flag

## 🔄 Communication Patterns

### REST API Communication
- **Synchronous Communication**: HTTP/HTTPS for client-server interaction
- **API Versioning**: URL-based versioning (`/api/v1/`)
- **Content Negotiation**: JSON request/response format
- **HTTP Status Codes**: Standard REST status codes

### Internal Service Communication
- **Direct Method Calls**: Within the same application
- **Repository Pattern**: Data access abstraction
- **Service Layer**: Business logic encapsulation
- **Transaction Management**: Declarative transaction handling

### Cache Communication
- **Redis Integration**: Session management and frequently accessed data
- **Cache Patterns**: Write-through, cache-aside, TTL-based expiration
- **Cache Strategies**: Multi-level caching approach

## 📊 Performance & Scalability

### Performance Optimization
- **Database Indexing**: Optimized query performance
- **Connection Pooling**: HikariCP configuration
- **Caching Strategy**: Multi-level caching approach
- **Async Processing**: Non-blocking operations

### Scalability Features
- **Horizontal Scaling**: Stateless design with JWT authentication
- **Load Balancing**: Nginx round-robin distribution
- **Database Scaling**: Read replicas and connection pooling
- **Cache Scaling**: Redis cluster support

### Resource Management
- **Memory Management**: JVM tuning and monitoring
- **CPU Optimization**: Thread pool configuration
- **I/O Optimization**: Database query optimization
- **Network Optimization**: Connection reuse and compression

## 🚀 Deployment Architecture

### Container Architecture
- **Docker Compose**: Multi-container orchestration
- **Service Dependencies**: Proper startup ordering
- **Volume Management**: Persistent data storage
- **Network Isolation**: Secure container communication

### Environment Support
- **Development**: Local Docker with H2 database
- **Production**: PostgreSQL with monitoring stack
- **Configuration**: Environment-specific properties
- **Health Checks**: Comprehensive health monitoring

### Monitoring & Observability
- **Metrics Collection**: Spring Boot Actuator + Prometheus
- **Logging**: Structured logging with correlation IDs
- **Health Checks**: Liveness and readiness probes
- **Alerting**: Performance and error rate monitoring

## 🔧 Development Guidelines

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

---

## 📖 Quick Start

1. **Read the Architecture**: Start with [ARCHITECTURE.md](./ARCHITECTURE.md) for comprehensive system overview
2. **Understand Communication**: Review [COMMUNICATION_PATTERNS.md](./COMMUNICATION_PATTERNS.md) for interaction patterns
3. **View Diagrams**: Explore the [diagrams](./diagrams/) directory for visual representations
4. **Deploy the System**: Follow the deployment guides in `/docs/deployment/`

## 🔄 Architecture Evolution

This architecture is designed to evolve with the system:
- **Microservices Ready**: Can be split into microservices when needed
- **Event-Driven**: Ready for event-driven architecture patterns
- **Cloud Native**: Compatible with cloud deployment strategies
- **API-First**: Designed for API-first development approach

For detailed implementation information, refer to the individual component documentation and the comprehensive architecture documents in this directory.
