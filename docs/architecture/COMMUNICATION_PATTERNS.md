# Communication Patterns Documentation

## Table of Contents
1. [Overview](#overview)
2. [REST API Communication](#rest-api-communication)
3. [Internal Service Communication](#internal-service-communication)
4. [Database Communication](#database-communication)
5. [Cache Communication](#cache-communication)
6. [External Service Integration](#external-service-integration)
7. [Future Messaging Patterns](#future-messaging-patterns)
8. [Error Handling & Resilience](#error-handling--resilience)
9. [Performance Considerations](#performance-considerations)

## Overview

The GreenCode backend system implements multiple communication patterns to ensure efficient, reliable, and scalable data exchange between different components. This document outlines the current communication patterns and provides guidance for future enhancements.

### Communication Pattern Categories
- **Synchronous Communication**: REST APIs, direct method calls
- **Asynchronous Communication**: Future event-driven patterns
- **Data Persistence**: Database and cache interactions
- **External Integration**: Third-party service communication

## REST API Communication

### API Design Principles

#### 1. RESTful Resource Design
```
Base URL: https://api.greencode.com/api/v1

Resources:
- /users          - User management
- /projects       - Project management
- /auth           - Authentication
- /health         - System health
```

#### 2. HTTP Methods and Semantics
```http
GET    /api/v1/users           # List all users
GET    /api/v1/users/{id}      # Get specific user
POST   /api/v1/users           # Create new user
PUT    /api/v1/users/{id}      # Update user
DELETE /api/v1/users/{id}      # Delete user
PATCH  /api/v1/users/{id}      # Partial update
```

#### 3. Request/Response Format
```json
// Request Example
POST /api/v1/users
Content-Type: application/json
Authorization: Bearer <jwt-token>

{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "securePassword123",
  "firstName": "John",
  "lastName": "Doe",
  "role": "USER"
}

// Response Example
HTTP/1.1 201 Created
Content-Type: application/json

{
  "id": 1,
  "username": "john_doe",
  "email": "john@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "role": "USER",
  "isEnabled": true,
  "createdAt": "2024-01-15T10:30:00Z",
  "updatedAt": "2024-01-15T10:30:00Z"
}
```

### API Versioning Strategy
- **URL-based versioning**: `/api/v1/`, `/api/v2/`
- **Backward compatibility**: Maintain previous versions
- **Deprecation policy**: 6-month notice for breaking changes

### Content Negotiation
- **Primary format**: JSON (application/json)
- **Error responses**: Consistent error format
- **Compression**: Gzip compression enabled
- **Character encoding**: UTF-8

## Internal Service Communication

### 1. Service Layer Communication

#### Direct Method Calls
```java
@Service
public class ProjectService {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AuditService auditService;
    
    public Project createProject(Project project, Long userId) {
        // Direct service call
        User manager = userService.getUserById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        
        // Business logic
        project.setManager(manager);
        Project savedProject = projectRepository.save(project);
        
        // Audit logging
        auditService.logProjectCreation(savedProject, manager);
        
        return savedProject;
    }
}
```

#### Transaction Management
```java
@Service
@Transactional
public class UserService {
    
    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public User createUser(User user) {
        // Multiple database operations in single transaction
        validateUserData(user);
        encodePassword(user);
        User savedUser = userRepository.save(user);
        sendWelcomeEmail(savedUser);
        return savedUser;
    }
}
```

### 2. Repository Pattern Communication

#### JPA Repository Communication
```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Spring Data JPA method queries
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    
    // Custom query methods
    @Query("SELECT u FROM User u WHERE u.role = :role AND u.isActive = true")
    List<User> findActiveUsersByRole(@Param("role") UserRole role);
    
    // Native queries for complex operations
    @Query(value = "SELECT * FROM users WHERE created_at >= :date", nativeQuery = true)
    List<User> findUsersCreatedAfter(@Param("date") LocalDateTime date);
}
```

#### Custom Repository Implementation
```java
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public boolean existsByUsernameOrEmailExcludingId(String username, String email, Long id) {
        String jpql = "SELECT COUNT(u) > 0 FROM User u WHERE " +
                     "(u.username = :username OR u.email = :email) AND u.id != :id";
        
        return entityManager.createQuery(jpql, Boolean.class)
            .setParameter("username", username)
            .setParameter("email", email)
            .setParameter("id", id)
            .getSingleResult();
    }
}
```

## Database Communication

### 1. Connection Management

#### Connection Pool Configuration
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/greencode
    username: postgres
    password: password
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
      leak-detection-threshold: 60000
```

#### Transaction Isolation Levels
```java
@Service
public class ProjectService {
    
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Project updateProject(Long id, Project projectDetails) {
        // Read committed isolation for consistency
        return projectRepository.save(projectDetails);
    }
    
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void transferProjectOwnership(Long projectId, Long newOwnerId) {
        // Serializable isolation for critical operations
        Project project = projectRepository.findById(projectId).orElseThrow();
        User newOwner = userService.getUserById(newOwnerId).orElseThrow();
        project.setManager(newOwner);
        projectRepository.save(project);
    }
}
```

### 2. Query Optimization

#### Lazy Loading Strategy
```java
@Entity
public class Project extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private User manager;
    
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<AuditLog> auditLogs;
}
```

#### Eager Loading for Performance
```java
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    @Query("SELECT p FROM Project p JOIN FETCH p.manager WHERE p.id = :id")
    Optional<Project> findByIdWithManager(@Param("id") Long id);
    
    @Query("SELECT p FROM Project p JOIN FETCH p.manager m WHERE p.status = :status")
    List<Project> findByStatusWithManager(@Param("status") ProjectStatus status);
}
```

## Cache Communication

### 1. Redis Integration

#### Cache Configuration
```java
@Configuration
@EnableCaching
public class CacheConfig {
    
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory factory = new LettuceConnectionFactory();
        factory.setHostName("localhost");
        factory.setPort(6379);
        return factory;
    }
    
    @Bean
    public CacheManager cacheManager() {
        RedisCacheManager.Builder builder = RedisCacheManager
            .RedisCacheManagerBuilder
            .fromConnectionFactory(redisConnectionFactory())
            .cacheDefaults(cacheConfiguration(Duration.ofMinutes(10)));
        
        return builder.build();
    }
}
```

#### Cache Usage Patterns
```java
@Service
public class UserService {
    
    @Cacheable(value = "users", key = "#id")
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    @Cacheable(value = "users", key = "#username")
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @CacheEvict(value = "users", key = "#user.id")
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    
    @CacheEvict(value = "users", allEntries = true)
    public void clearUserCache() {
        // Clear all user cache entries
    }
}
```

### 2. Cache Strategies

#### Write-Through Caching
```java
@Service
public class SessionService {
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    public void cacheUserSession(User user, String token) {
        String key = "session:" + token;
        UserSession session = new UserSession(user, token, Duration.ofHours(24));
        redisTemplate.opsForValue().set(key, session, Duration.ofHours(24));
    }
    
    public Optional<UserSession> getUserSession(String token) {
        String key = "session:" + token;
        return Optional.ofNullable((UserSession) redisTemplate.opsForValue().get(key));
    }
}
```

#### Cache-Aside Pattern
```java
@Service
public class ProjectService {
    
    public Project getProjectById(Long id) {
        // Check cache first
        String cacheKey = "project:" + id;
        Project cachedProject = (Project) redisTemplate.opsForValue().get(cacheKey);
        
        if (cachedProject != null) {
            return cachedProject;
        }
        
        // Load from database
        Project project = projectRepository.findById(id)
            .orElseThrow(() -> new ProjectNotFoundException("Project not found"));
        
        // Cache the result
        redisTemplate.opsForValue().set(cacheKey, project, Duration.ofMinutes(30));
        
        return project;
    }
}
```

## External Service Integration

### 1. HTTP Client Communication

#### RestTemplate Configuration
```java
@Configuration
public class RestTemplateConfig {
    
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        
        // Connection timeout
        HttpComponentsClientHttpRequestFactory factory = 
            new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(10000);
        restTemplate.setRequestFactory(factory);
        
        // Error handling
        restTemplate.setErrorHandler(new CustomResponseErrorHandler());
        
        return restTemplate;
    }
}
```

#### External API Integration
```java
@Service
public class NotificationService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${external.email.service.url}")
    private String emailServiceUrl;
    
    public void sendWelcomeEmail(User user) {
        try {
            EmailRequest emailRequest = new EmailRequest(
                user.getEmail(),
                "Welcome to GreenCode",
                buildWelcomeEmailContent(user)
            );
            
            ResponseEntity<EmailResponse> response = restTemplate.postForEntity(
                emailServiceUrl + "/send",
                emailRequest,
                EmailResponse.class
            );
            
            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Welcome email sent to user: {}", user.getEmail());
            }
        } catch (Exception e) {
            log.error("Failed to send welcome email to user: {}", user.getEmail(), e);
            // Don't throw exception - email failure shouldn't break user registration
        }
    }
}
```

### 2. Circuit Breaker Pattern

#### Resilience4j Configuration
```java
@Configuration
public class CircuitBreakerConfig {
    
    @Bean
    public CircuitBreaker emailServiceCircuitBreaker() {
        return CircuitBreaker.ofDefaults("emailService")
            .toBuilder()
            .slidingWindowSize(10)
            .failureRateThreshold(50)
            .waitDurationInOpenState(Duration.ofSeconds(30))
            .build();
    }
}
```

#### Circuit Breaker Usage
```java
@Service
public class NotificationService {
    
    @Autowired
    private CircuitBreaker emailServiceCircuitBreaker;
    
    public void sendWelcomeEmail(User user) {
        Supplier<Void> decoratedSupplier = CircuitBreaker
            .decorateSupplier(emailServiceCircuitBreaker, () -> {
                sendEmailInternal(user);
                return null;
            });
        
        try {
            decoratedSupplier.get();
        } catch (CallNotPermittedException e) {
            log.warn("Email service circuit breaker is open, email not sent");
        } catch (Exception e) {
            log.error("Failed to send email", e);
        }
    }
}
```

## Future Messaging Patterns

### 1. Event-Driven Architecture

#### Domain Events
```java
@Entity
public class Project extends BaseEntity {
    
    @DomainEvents
    Collection<Object> domainEvents() {
        return Arrays.asList(new ProjectCreatedEvent(this));
    }
    
    @AfterDomainEventPublication
    void callbackMethod() {
        // Clear domain events after publication
    }
}

@EventListener
@Component
public class ProjectEventHandler {
    
    @Async
    @EventListener
    public void handleProjectCreated(ProjectCreatedEvent event) {
        // Send notifications
        // Update search index
        // Generate reports
    }
}
```

#### Message Queue Integration (Future)
```java
@Service
public class ProjectEventPublisher {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    public void publishProjectCreated(Project project) {
        ProjectCreatedMessage message = new ProjectCreatedMessage(
            project.getId(),
            project.getName(),
            project.getManager().getId()
        );
        
        rabbitTemplate.convertAndSend("project.exchange", "project.created", message);
    }
}
```

### 2. WebSocket Communication

#### WebSocket Configuration (Future)
```java
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new ProjectUpdateHandler(), "/ws/projects")
                .setAllowedOrigins("*");
    }
}
```

#### Real-time Notifications
```java
@Component
public class ProjectUpdateHandler extends TextWebSocketHandler {
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // Handle new WebSocket connection
    }
    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        // Handle incoming messages
    }
    
    public void broadcastProjectUpdate(Project project) {
        // Broadcast project updates to all connected clients
    }
}
```

## Error Handling & Resilience

### 1. Retry Patterns

#### Spring Retry Configuration
```java
@Configuration
@EnableRetry
public class RetryConfig {
    
    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(2000); // 2 seconds
        retryTemplate.setBackOffPolicy(backOffPolicy);
        
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);
        retryTemplate.setRetryPolicy(retryPolicy);
        
        return retryTemplate;
    }
}
```

#### Retry Usage
```java
@Service
public class ExternalServiceClient {
    
    @Autowired
    private RetryTemplate retryTemplate;
    
    public String callExternalService(String data) {
        return retryTemplate.execute(context -> {
            try {
                return restTemplate.postForObject("/external-api", data, String.class);
            } catch (Exception e) {
                log.warn("External service call failed, attempt: {}", context.getRetryCount() + 1);
                throw e;
            }
        });
    }
}
```

### 2. Timeout Configuration

#### Global Timeout Settings
```yaml
spring:
  datasource:
    hikari:
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
  
  redis:
    timeout: 2000ms
    lettuce:
      pool:
        max-active: 8
        max-idle: 8
        min-idle: 0
```

#### Service-Level Timeouts
```java
@Service
public class ExternalApiService {
    
    @Async("externalApiExecutor")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    public CompletableFuture<String> callExternalApi(String data) {
        return CompletableFuture.supplyAsync(() -> {
            // External API call
            return restTemplate.postForObject("/api/external", data, String.class);
        });
    }
}
```

## Performance Considerations

### 1. Connection Pooling

#### Database Connection Pool
```yaml
spring:
  datasource:
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
      leak-detection-threshold: 60000
```

#### HTTP Connection Pool
```java
@Configuration
public class HttpClientConfig {
    
    @Bean
    public CloseableHttpClient httpClient() {
        return HttpClients.custom()
            .setMaxConnTotal(100)
            .setMaxConnPerRoute(20)
            .setConnectionTimeToLive(30, TimeUnit.SECONDS)
            .build();
    }
}
```

### 2. Async Processing

#### Async Configuration
```java
@Configuration
@EnableAsync
public class AsyncConfig {
    
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("Async-");
        executor.initialize();
        return executor;
    }
}
```

#### Async Service Methods
```java
@Service
public class NotificationService {
    
    @Async("taskExecutor")
    public CompletableFuture<Void> sendEmailAsync(User user) {
        try {
            sendWelcomeEmail(user);
            return CompletableFuture.completedFuture(null);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }
}
```

### 3. Batch Processing

#### Batch Repository Operations
```java
@Repository
public class ProjectRepositoryImpl {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public void batchInsertProjects(List<Project> projects) {
        for (int i = 0; i < projects.size(); i++) {
            entityManager.persist(projects.get(i));
            
            if (i % 50 == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }
}
```

---

## Conclusion

This communication patterns documentation provides a comprehensive overview of how different components in the GreenCode backend system interact with each other. The patterns described here ensure:

- **Reliability**: Robust error handling and retry mechanisms
- **Performance**: Efficient connection pooling and caching strategies
- **Scalability**: Async processing and batch operations
- **Maintainability**: Clear separation of concerns and consistent patterns

As the system evolves, these patterns can be extended to support more advanced communication mechanisms like event-driven architecture and real-time messaging.
