# GreenCode API Guide

This document provides concrete request/response examples for the GreenCode
backend API. All endpoints are prefixed with `/api` (configured via
`SERVER_CONTEXT_PATH`).

## Base URL

```
http://localhost:8080/api
```

## Authentication

### Register a New User

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "jane.doe",
    "email": "jane@example.com",
    "password": "SecureP@ss1",
    "firstName": "Jane",
    "lastName": "Doe"
  }'
```

**Success Response** (`201 Created`):

```json
{
  "id": 1,
  "username": "jane.doe",
  "email": "jane@example.com",
  "firstName": "Jane",
  "lastName": "Doe",
  "createdAt": "2025-01-15T10:30:00Z"
}
```

**Validation Error** (`400 Bad Request`):

```json
{
  "timestamp": "2025-01-15T10:30:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "details": [
    { "field": "email", "message": "must be a valid email address" },
    { "field": "password", "message": "must be at least 8 characters" }
  ]
}
```

### Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "jane.doe",
    "password": "SecureP@ss1"
  }'
```

**Success Response** (`200 OK`):

```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9...",
  "tokenType": "Bearer",
  "expiresIn": 3600
}
```

**Invalid Credentials** (`401 Unauthorized`):

```json
{
  "timestamp": "2025-01-15T10:31:00Z",
  "status": 401,
  "error": "Unauthorized",
  "message": "Invalid username or password"
}
```

### Password Reset Request

```bash
curl -X POST http://localhost:8080/api/auth/password-reset \
  -H "Content-Type: application/json" \
  -d '{ "email": "jane@example.com" }'
```

**Success Response** (`200 OK`):

```json
{
  "message": "Password reset instructions sent to your email"
}
```

## Health Check

```bash
curl http://localhost:8080/api/actuator/health
```

**Response** (`200 OK`):

```json
{
  "status": "UP",
  "components": {
    "db": { "status": "UP" },
    "diskSpace": { "status": "UP" }
  }
}
```

> **Note:** The context path means the health endpoint is at
> `/api/actuator/health`, **not** `/actuator/health`. See issue #28 for
> background.

## Error Response Format

All error responses follow a consistent structure:

| Field       | Type   | Description                              |
|-------------|--------|------------------------------------------|
| `timestamp` | string | ISO-8601 timestamp of the error          |
| `status`    | int    | HTTP status code                         |
| `error`     | string | HTTP status reason phrase                |
| `message`   | string | Human-readable error description         |
| `details`   | array  | (optional) Field-level validation errors |

### Common HTTP Status Codes

| Code | Meaning               | Typical Cause                          |
|------|-----------------------|----------------------------------------|
| 200  | OK                    | Successful read or update              |
| 201  | Created               | Resource created successfully          |
| 400  | Bad Request           | Validation failure or malformed JSON   |
| 401  | Unauthorized          | Missing or expired token               |
| 403  | Forbidden             | Insufficient permissions               |
| 404  | Not Found             | Resource does not exist                |
| 409  | Conflict              | Duplicate resource (e.g., username)    |
| 500  | Internal Server Error | Unexpected server-side failure         |

## Using the Swagger UI

When the application is running, interactive API documentation is available at:

```
http://localhost:8080/api/swagger-ui.html
```

The OpenAPI spec (JSON) can be downloaded from:

```
http://localhost:8080/api/v3/api-docs
```
