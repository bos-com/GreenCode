# GreenCode Deployment Guide

This directory contains deployment guides and configurations for different environments.

## 🚀 Deployment Options

### 1. Docker Compose (Recommended for Development)
- **File**: `docker-compose.yml`
- **Usage**: `docker-compose up -d`
- **Benefits**: Easy setup, consistent environment

### 2. Docker (Production)
- **File**: `Dockerfile`
- **Usage**: `docker build -t greencode-backend .`
- **Benefits**: Containerized, portable

### 3. Traditional JAR Deployment
- **File**: `deploy.sh`
- **Usage**: `./deploy.sh`
- **Benefits**: Direct control, no container overhead

## 🌍 Environment Configurations

### Development
- **Profile**: `dev`
- **Database**: H2 (in-memory)
- **Logging**: DEBUG level
- **Port**: 8080

### Production
- **Profile**: `prod`
- **Database**: PostgreSQL
- **Logging**: INFO level
- **Port**: 8080 (configurable)

### Testing
- **Profile**: `test`
- **Database**: H2 (test)
- **Logging**: WARN level

## 📋 Prerequisites

- Java 17+
- Maven 3.6+
- Docker & Docker Compose
- PostgreSQL (production)

## 🔧 Quick Deploy

```bash
# Clone and setup
git clone <repository>
cd GreenCode

# Development with Docker
docker-compose up -d

# Production build
mvn clean package -Pprod
java -jar target/greencode-backend-1.0.0.jar
```

## 📊 Monitoring

- **Health**: `/api/actuator/health`
- **Metrics**: `/api/actuator/metrics`
- **Info**: `/api/actuator/info`
- **Logs**: Check container logs

## 🆘 Troubleshooting

Common issues and solutions are documented in the troubleshooting guide.
