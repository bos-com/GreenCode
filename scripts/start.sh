#!/bin/bash

# GreenCode Startup Script

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(dirname "$SCRIPT_DIR")"

cd "$PROJECT_ROOT"

echo "🚀 Starting GreenCode..."

# Check if .env exists
if [ ! -f .env ]; then
    echo "❌ ERROR: .env file not found!"
    echo "Please run ./scripts/setup.sh first"
    exit 1
fi

# Load environment variables
set -a
source .env
set +a

# Create logs directory if it doesn't exist
mkdir -p logs

# Start the application
echo "Starting Spring Boot application..."
nohup mvn spring-boot:run > logs/application.log 2>&1 &
echo $! > .pid

echo "✅ GreenCode started!"
echo "   Access the application: http://localhost:8080"
echo "   Swagger UI: http://localhost:8080/api/swagger-ui.html"
echo "   Logs: logs/application.log"
