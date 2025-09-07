# Data Flow Architecture Diagrams

## User Registration Flow

```mermaid
sequenceDiagram
    participant Client
    participant Nginx
    participant UserController
    participant UserService
    participant PasswordEncoder
    participant UserRepository
    participant PostgreSQL
    participant Redis
    
    Client->>Nginx: POST /api/users (User Data)
    Nginx->>UserController: Forward Request
    UserController->>UserController: Validate Input (DTO)
    UserController->>UserService: createUser(user)
    
    UserService->>UserRepository: existsByUsername(username)
    UserRepository->>PostgreSQL: SELECT username FROM users
    PostgreSQL-->>UserRepository: Result
    UserRepository-->>UserService: false (not exists)
    
    UserService->>UserRepository: existsByEmail(email)
    UserRepository->>PostgreSQL: SELECT email FROM users
    PostgreSQL-->>UserRepository: Result
    UserRepository-->>UserService: false (not exists)
    
    UserService->>PasswordEncoder: encode(password)
    PasswordEncoder-->>UserService: hashedPassword
    
    UserService->>UserRepository: save(user)
    UserRepository->>PostgreSQL: INSERT INTO users
    PostgreSQL-->>UserRepository: Saved User
    UserRepository-->>UserService: User Entity
    
    UserService->>Redis: cacheUser(user)
    Redis-->>UserService: Cached
    
    UserService-->>UserController: Created User
    UserController-->>Nginx: 201 Created + User Data
    Nginx-->>Client: HTTP Response
```

## Project Creation Flow

```mermaid
sequenceDiagram
    participant Client
    participant Nginx
    participant ProjectController
    participant ProjectService
    participant UserService
    participant ProjectRepository
    participant AuditService
    participant PostgreSQL
    participant Redis
    
    Client->>Nginx: POST /api/projects (Project Data + JWT)
    Nginx->>ProjectController: Forward Request
    ProjectController->>ProjectController: Validate JWT Token
    ProjectController->>ProjectController: Validate Input (DTO)
    ProjectController->>ProjectService: createProject(project, userId)
    
    ProjectService->>UserService: getUserById(userId)
    UserService->>Redis: getCachedUser(userId)
    alt Cache Hit
        Redis-->>UserService: Cached User
    else Cache Miss
        UserService->>PostgreSQL: SELECT * FROM users WHERE id = ?
        PostgreSQL-->>UserService: User Data
        UserService->>Redis: cacheUser(user)
    end
    UserService-->>ProjectService: User Entity
    
    ProjectService->>ProjectService: Validate Business Rules
    ProjectService->>ProjectRepository: save(project)
    ProjectRepository->>PostgreSQL: INSERT INTO projects
    PostgreSQL-->>ProjectRepository: Saved Project
    ProjectRepository-->>ProjectService: Project Entity
    
    ProjectService->>AuditService: logProjectCreation(project, user)
    AuditService->>PostgreSQL: INSERT INTO audit_logs
    PostgreSQL-->>AuditService: Logged
    
    ProjectService->>Redis: cacheProject(project)
    Redis-->>ProjectService: Cached
    
    ProjectService-->>ProjectController: Created Project
    ProjectController-->>Nginx: 201 Created + Project Data
    Nginx-->>Client: HTTP Response
```

## Authentication Flow

```mermaid
sequenceDiagram
    participant Client
    participant Nginx
    participant AuthController
    participant AuthService
    participant UserService
    participant PasswordEncoder
    participant JwtUtils
    participant PostgreSQL
    participant Redis
    
    Client->>Nginx: POST /api/auth/login (credentials)
    Nginx->>AuthController: Forward Request
    AuthController->>AuthController: Validate Input
    AuthController->>AuthService: authenticate(username, password)
    
    AuthService->>UserService: getUserByUsername(username)
    UserService->>Redis: getCachedUser(username)
    alt Cache Hit
        Redis-->>UserService: Cached User
    else Cache Miss
        UserService->>PostgreSQL: SELECT * FROM users WHERE username = ?
        PostgreSQL-->>UserService: User Data
        UserService->>Redis: cacheUser(user)
    end
    UserService-->>AuthService: User Entity
    
    AuthService->>AuthService: Check if user is enabled
    AuthService->>PasswordEncoder: matches(password, user.password)
    PasswordEncoder-->>AuthService: true/false
    
    alt Password Valid
        AuthService->>JwtUtils: generateToken(user)
        JwtUtils-->>AuthService: JWT Token
        AuthService->>Redis: cacheUserSession(user, token)
        Redis-->>AuthService: Session Cached
        AuthService-->>AuthController: AuthResponse (token, user)
        AuthController-->>Nginx: 200 OK + Token
    else Password Invalid
        AuthService-->>AuthController: Authentication Failed
        AuthController-->>Nginx: 401 Unauthorized
    end
    
    Nginx-->>Client: HTTP Response
```

## Data Synchronization Flow

```mermaid
graph TB
    subgraph "Application Layer"
        SERVICE[Service Layer]
        CACHE_MANAGER[Cache Manager]
    end
    
    subgraph "Cache Layer"
        REDIS[Redis Cache<br/>Session Data<br/>Frequently Accessed Data]
    end
    
    subgraph "Database Layer"
        POSTGRES[PostgreSQL<br/>Master Database<br/>Persistent Storage]
    end
    
    subgraph "Cache Strategies"
        WRITE_THROUGH[Write-Through<br/>User Sessions]
        CACHE_ASIDE[Cache-Aside<br/>Project Data]
        TTL[TTL Expiration<br/>Temporary Data]
    end
    
    SERVICE --> CACHE_MANAGER
    CACHE_MANAGER --> REDIS
    CACHE_MANAGER --> POSTGRES
    
    REDIS --> WRITE_THROUGH
    REDIS --> CACHE_ASIDE
    REDIS --> TTL
    
    classDef app fill:#e8f5e8
    classDef cache fill:#fff3e0
    classDef db fill:#e3f2fd
    classDef strategy fill:#fce4ec
    
    class SERVICE,CACHE_MANAGER app
    class REDIS cache
    class POSTGRES db
    class WRITE_THROUGH,CACHE_ASIDE,TTL strategy
```

## Error Handling Flow

```mermaid
flowchart TD
    START([Request]) --> CONTROLLER[Controller]
    CONTROLLER --> VALIDATION{Input Valid?}
    
    VALIDATION -->|No| VALIDATION_ERROR[ValidationException]
    VALIDATION -->|Yes| SERVICE[Service Layer]
    
    SERVICE --> BUSINESS_LOGIC{Business Rules<br/>Valid?}
    BUSINESS_LOGIC -->|No| BUSINESS_ERROR[BusinessException]
    BUSINESS_LOGIC -->|Yes| REPOSITORY[Repository]
    
    REPOSITORY --> DATABASE{Database<br/>Operation}
    DATABASE -->|Success| SUCCESS[Success Response]
    DATABASE -->|Constraint Violation| CONSTRAINT_ERROR[DataIntegrityException]
    DATABASE -->|Connection Error| CONNECTION_ERROR[DataAccessException]
    DATABASE -->|Not Found| NOT_FOUND[EntityNotFoundException]
    
    VALIDATION_ERROR --> GLOBAL_HANDLER[GlobalExceptionHandler]
    BUSINESS_ERROR --> GLOBAL_HANDLER
    CONSTRAINT_ERROR --> GLOBAL_HANDLER
    CONNECTION_ERROR --> GLOBAL_HANDLER
    NOT_FOUND --> GLOBAL_HANDLER
    
    GLOBAL_HANDLER --> LOG_ERROR[Log Error]
    LOG_ERROR --> STRUCTURED_RESPONSE[Structured Error Response]
    STRUCTURED_RESPONSE --> CLIENT[Client]
    
    SUCCESS --> CLIENT
    
    classDef start fill:#e8f5e8
    classDef process fill:#e3f2fd
    classDef decision fill:#fff3e0
    classDef error fill:#ffebee
    classDef success fill:#e8f5e8
    classDef end fill:#f3e5f5
    
    class START start
    class CONTROLLER,SERVICE,REPOSITORY process
    class VALIDATION,BUSINESS_LOGIC,DATABASE decision
    class VALIDATION_ERROR,BUSINESS_ERROR,CONSTRAINT_ERROR,CONNECTION_ERROR,NOT_FOUND error
    class SUCCESS success
    class GLOBAL_HANDLER,LOG_ERROR,STRUCTURED_RESPONSE,CLIENT end
```

## Monitoring Data Flow

```mermaid
graph TB
    subgraph "Application Metrics"
        ACTUATOR[Spring Boot Actuator<br/>Health, Metrics, Info]
        CUSTOM_METRICS[Custom Business Metrics<br/>Project Count, User Activity]
    end
    
    subgraph "Infrastructure Metrics"
        JVM_METRICS[JVM Metrics<br/>Memory, CPU, GC]
        DB_METRICS[Database Metrics<br/>Connections, Queries]
        CACHE_METRICS[Cache Metrics<br/>Hit Rate, Size]
    end
    
    subgraph "Monitoring Stack"
        PROMETHEUS[Prometheus<br/>Metrics Collection<br/>Time Series DB]
        GRAFANA[Grafana<br/>Visualization<br/>Dashboards]
    end
    
    subgraph "Alerting"
        ALERT_RULES[Alert Rules<br/>Thresholds<br/>Conditions]
        NOTIFICATIONS[Notifications<br/>Email, Slack<br/>PagerDuty]
    end
    
    ACTUATOR --> PROMETHEUS
    CUSTOM_METRICS --> PROMETHEUS
    JVM_METRICS --> PROMETHEUS
    DB_METRICS --> PROMETHEUS
    CACHE_METRICS --> PROMETHEUS
    
    PROMETHEUS --> GRAFANA
    PROMETHEUS --> ALERT_RULES
    ALERT_RULES --> NOTIFICATIONS
    
    classDef app fill:#e8f5e8
    classDef infra fill:#fff3e0
    classDef monitor fill:#e3f2fd
    classDef alert fill:#fce4ec
    
    class ACTUATOR,CUSTOM_METRICS app
    class JVM_METRICS,DB_METRICS,CACHE_METRICS infra
    class PROMETHEUS,GRAFANA monitor
    class ALERT_RULES,NOTIFICATIONS alert
```
