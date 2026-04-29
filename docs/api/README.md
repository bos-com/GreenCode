# GreenCode API Documentation

This guide documents the routes that are currently implemented in the tracked
source tree. Keep it aligned with the controller mappings in `src/main/java`
and the runtime configuration in `src/main/resources/application.yml`.

## Base URL

The application runs under the `/api` servlet context by default:

```text
http://localhost:8080/api
```

## Documentation and Health Endpoints

- Swagger UI: `http://localhost:8080/api/swagger-ui.html`
- OpenAPI spec: `http://localhost:8080/api/api-docs`
- Health endpoint: `http://localhost:8080/api/actuator/health`

## Implemented Controller Routes

The current repository exposes a user-management controller with these routes:

| Method | Path | Purpose |
| --- | --- | --- |
| `GET` | `/api/users` | List all users |
| `GET` | `/api/users/{id}` | Fetch one user by numeric ID |
| `GET` | `/api/users/username/{username}` | Fetch one user by username |
| `GET` | `/api/users/email/{email}` | Fetch one user by email |
| `POST` | `/api/users` | Create a user |
| `PUT` | `/api/users/{id}` | Replace a user |
| `DELETE` | `/api/users/{id}` | Delete a user |
| `GET` | `/api/users/check/username/{username}` | Check whether a username exists |
| `GET` | `/api/users/check/email/{email}` | Check whether an email exists |

## Example Requests

### Health check

```bash
curl http://localhost:8080/api/actuator/health
```

### Create a user

```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "janedoe",
    "email": "jane@example.com",
    "password": "change-me",
    "firstName": "Jane",
    "lastName": "Doe",
    "role": "USER",
    "isEnabled": true
  }'
```

## Authentication Note

The security configuration already reserves `/api/auth/**` for future
authentication work, but the current tracked source tree does not yet include a
dedicated authentication controller. Do not document login or token endpoints
as available until they exist in the application code.
