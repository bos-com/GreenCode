# GreenCode System Architecture

This directory contains system architecture documentation and diagrams for the GreenCode backend.

## 🏗️ System Overview

GreenCode is built using a layered architecture pattern with clear separation of concerns:

```
┌─────────────────────────────────────────────────────────────┐
│                    Presentation Layer                       │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐ │
│  │   REST API  │  │  Swagger UI │  │   Health Endpoints  │ │
│  └─────────────┘  └─────────────┘  └─────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────────────────┐
│                     Business Layer                          │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐ │
│  │ User Service│  │Auth Service │  │  Project Service    │ │
│  └─────────────┘  └─────────────┘  └─────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────────────────┐
│                    Data Access Layer                        │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐ │
│  │User Repo    │  │Project Repo │  │   Audit Repository  │ │
│  └─────────────┘  └─────────────┘  └─────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────────────────┐
│                     Data Layer                              │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐ │
│  │ PostgreSQL  │  │     H2      │  │    File Storage     │ │
│  └─────────────┘  └─────────────┘  └─────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
```

## 🔐 Security Architecture

### Authentication Flow
1. **Login Request** → User credentials
2. **Validation** → BCrypt password verification
3. **JWT Generation** → Signed token with claims
4. **Response** → JWT token + refresh token

### Authorization
- **Role-Based Access Control (RBAC)**
- **Resource-Level Permissions**
- **API Rate Limiting**

## 🗄️ Database Design

### Core Entities
- **User**: Authentication and profile data
- **Project**: Environmental project information
- **AuditLog**: System activity tracking
- **Role**: User permissions and access levels

### Relationships
- User ↔ Role (Many-to-Many)
- User ↔ Project (One-to-Many)
- Project ↔ AuditLog (One-to-Many)

## 🔄 Data Flow

### Request Processing
1. **HTTP Request** → Controller
2. **Validation** → DTO validation
3. **Business Logic** → Service layer
4. **Data Access** → Repository layer
5. **Response** → DTO transformation

### Error Handling
- **Global Exception Handler**
- **Structured Error Responses**
- **Logging and Monitoring**

## 📊 Performance Considerations

- **Connection Pooling** for database
- **Caching Strategy** for frequently accessed data
- **Async Processing** for long-running operations
- **Database Indexing** for query optimization

## 🔧 Configuration Management

- **Environment-Specific Configs**
- **Externalized Properties**
- **Feature Flags**
- **Health Checks**

## 🚀 Scalability

- **Horizontal Scaling** with load balancers
- **Database Sharding** for large datasets
- **Microservices** architecture ready
- **Container Orchestration** support
