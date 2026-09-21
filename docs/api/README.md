# 📘 GreenCode API Documentation

This directory contains comprehensive API documentation for the **GreenCode backend system**.

---

## 📚 Documentation Structure

- **OpenAPI Specification** – Complete API schema (OpenAPI 3.0)
- **Endpoint Guides** – Detailed documentation for each API endpoint
- **Authentication** – JWT authentication flow and examples
- **Error Codes** – Complete list of error responses
- **Rate Limiting** – API usage limits and guidelines

---

## 🔗 Quick Links

- [API Endpoints](#)
- [Authentication Guide](#)
- [Error Reference](#)
- [Rate Limiting](#)
- [Postman Collection](#)

---

## 🚀 Getting Started

| Item | Value |
|------|------|
| Base URL | `http://localhost:8080/api` |
| Authentication | Bearer Token |
| Content-Type | `application/json` |
| API Version | v1 |

---

## 🔐 Authentication Example

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "user",
    "password": "password"
  }'
🔑 Accessing Protected Endpoints
curl -X GET http://localhost:8080/api/users \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
🔧 Development Tools
Swagger UI: http://localhost:8080/swagger-ui.html
OpenAPI Spec: http://localhost:8080/api-docs
Health Check: http://localhost:8080/health
📌 Notes
Always include the Authorization header for protected routes
Ensure your token has not expired
Use Swagger UI for interactive API testing
🧪 Best Practices
Validate request payloads before sending
Handle error responses gracefully
Use environment variables for API URLs
📬 Support

For API issues, open an issue in this repository with:

Endpoint name
Request payload
Error response

---

# 🧾 STEP 2: Commit

### Commit message
```text
Improve API documentation structure and readability
🚀 STEP 3: Create PR
PR Title
Improve API documentation formatting and clarity
PR Description
This pull request improves the GreenCode API documentation.

Fixes #ISSUE_NUMBER

Changes made:
- Added clear structure and sections
- Improved formatting using tables and code blocks
- Added examples for authentication and protected endpoints
- Included development tools and best practices

Importance:
Improves developer experience and makes the API easier to unders
