# GreenCode API Documentation

This directory contains comprehensive API documentation for the GreenCode backend system.

## 📚 Documentation Structure

- **OpenAPI Specification**: Complete API schema in OpenAPI 3.0 format
- **Endpoint Guides**: Detailed documentation for each API endpoint
- **Authentication**: JWT authentication flow and examples
- **Error Codes**: Complete list of error responses
- **Rate Limiting**: API usage limits and guidelines

## 🔗 Quick Links

- [API Endpoints](./endpoints.md)
- [Authentication Guide](./authentication.md)
- [Error Reference](./errors.md)
- [Rate Limiting](./rate-limiting.md)
- [Postman Collection](./postman/)

## 🚀 Getting Started

1. **Base URL**: `http://localhost:8080/api`
2. **Authentication**: Bearer token in Authorization header
3. **Content-Type**: `application/json`
4. **API Version**: v1 (included in URL path)

## 📖 Examples

### Authentication
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "user", "password": "password"}'
```

### Protected Endpoint
```bash
curl -X GET http://localhost:8080/api/users \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## 🔧 Development

- **Swagger UI**: Available at `/swagger-ui.html`
- **OpenAPI Spec**: Available at `/api-docs`
- **Health Check**: Available at `/api/actuator/health`
- **Actuator Metrics**: Available at `/api/actuator/metrics`
- **Actuator Info**: Available at `/api/actuator/info`
