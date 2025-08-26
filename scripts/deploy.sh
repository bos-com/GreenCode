#!/bin/bash

# GreenCode Deployment Script
# This script deploys the GreenCode backend to different environments

set -e

# Configuration
APP_NAME="greencode-backend"
APP_VERSION="1.0.0"
JAR_FILE="target/${APP_NAME}-${APP_VERSION}.jar"

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Default values
ENVIRONMENT="dev"
PROFILE="dev"
PORT="8080"
HEAP_SIZE="512m"
JVM_OPTS=""

# Help function
show_help() {
    echo "Usage: $0 [OPTIONS]"
    echo ""
    echo "Options:"
    echo "  -e, --environment ENV    Deployment environment (dev, staging, prod) [default: dev]"
    echo "  -p, --port PORT          Application port [default: 8080]"
    echo "  -m, --memory SIZE        JVM heap size [default: 512m]"
    echo "  -j, --jvm-opts OPTS      Additional JVM options"
    echo "  -h, --help               Show this help message"
    echo ""
    echo "Examples:"
    echo "  $0 -e dev                # Deploy to development"
    echo "  $0 -e prod -p 9090       # Deploy to production on port 9090"
    echo "  $0 -e staging -m 1g      # Deploy to staging with 1GB heap"
}

# Parse command line arguments
while [[ $# -gt 0 ]]; do
    case $1 in
        -e|--environment)
            ENVIRONMENT="$2"
            shift 2
            ;;
        -p|--port)
            PORT="$2"
            shift 2
            ;;
        -m|--memory)
            HEAP_SIZE="$2"
            shift 2
            ;;
        -j|--jvm-opts)
            JVM_OPTS="$2"
            shift 2
            ;;
        -h|--help)
            show_help
            exit 0
            ;;
        *)
            echo -e "${RED}❌ Unknown option: $1${NC}"
            show_help
            exit 1
            ;;
    esac
done

# Set profile based on environment
case $ENVIRONMENT in
    dev)
        PROFILE="dev"
        ;;
    staging)
        PROFILE="staging"
        ;;
    prod)
        PROFILE="prod"
        ;;
    *)
        echo -e "${RED}❌ Invalid environment: $ENVIRONMENT${NC}"
        echo "Valid environments: dev, staging, prod"
        exit 1
        ;;
esac

echo "🚀 Deploying GreenCode Backend..."
echo "Environment: $ENVIRONMENT"
echo "Profile: $PROFILE"
echo "Port: $PORT"
echo "Heap Size: $HEAP_SIZE"
echo ""

# Check if JAR file exists
if [ ! -f "$JAR_FILE" ]; then
    echo -e "${YELLOW}⚠️  JAR file not found. Building project first...${NC}"
    mvn clean package -DskipTests
fi

# Stop existing application if running
stop_existing() {
    echo "🛑 Stopping existing application..."
    if [ -f ".pid" ]; then
        PID=$(cat .pid)
        if ps -p $PID > /dev/null 2>&1; then
            echo "Stopping process $PID..."
            kill $PID
            sleep 5
            if ps -p $PID > /dev/null 2>&1; then
                echo "Force killing process $PID..."
                kill -9 $PID
            fi
            echo -e "${GREEN}✅ Application stopped${NC}"
        else
            echo "No running application found"
        fi
        rm -f .pid
    else
        echo "No PID file found"
    fi
}

# Create deployment directories
create_directories() {
    echo "📁 Creating deployment directories..."
    mkdir -p logs
    mkdir -p data
    mkdir -p reports
    echo -e "${GREEN}✅ Directories created${NC}"
}

# Deploy application
deploy_application() {
    echo "🚀 Deploying application..."
    
    # Set JVM options
    JAVA_OPTS="-Xmx${HEAP_SIZE} -Xms${HEAP_SIZE}"
    if [ ! -z "$JVM_OPTS" ]; then
        JAVA_OPTS="$JAVA_OPTS $JVM_OPTS"
    fi
    
    # Start application
    nohup java $JAVA_OPTS \
        -Dspring.profiles.active=$PROFILE \
        -Dserver.port=$PORT \
        -jar "$JAR_FILE" \
        > logs/application.log 2>&1 &
    
    # Save PID
    echo $! > .pid
    
    echo -e "${GREEN}✅ Application deployed with PID $(cat .pid)${NC}"
}

# Wait for application to start
wait_for_startup() {
    echo "⏳ Waiting for application to start..."
    local max_attempts=30
    local attempt=1
    
    while [ $attempt -le $max_attempts ]; do
        if curl -s "http://localhost:$PORT/api/health" > /dev/null 2>&1; then
            echo -e "${GREEN}✅ Application is running!${NC}"
            return 0
        fi
        
        echo "Attempt $attempt/$max_attempts - Application not ready yet..."
        sleep 2
        attempt=$((attempt + 1))
    done
    
    echo -e "${RED}❌ Application failed to start within expected time${NC}"
    echo "Check logs/application.log for details"
    return 1
}

# Show deployment info
show_deployment_info() {
    echo ""
    echo "🎉 Deployment completed successfully!"
    echo ""
    echo "Application Information:"
    echo "  - Environment: $ENVIRONMENT"
    echo "  - Profile: $PROFILE"
    echo "  - Port: $PORT"
    echo "  - PID: $(cat .pid)"
    echo "  - Heap Size: $HEAP_SIZE"
    echo ""
    echo "Access Points:"
    echo "  - Application: http://localhost:$PORT"
    echo "  - API Base: http://localhost:$PORT/api"
    echo "  - Health Check: http://localhost:$PORT/api/health"
    echo "  - Swagger UI: http://localhost:$PORT/api/swagger-ui.html"
    echo ""
    echo "Management:"
    echo "  - View logs: tail -f logs/application.log"
    echo "  - Stop app: ./scripts/stop.sh"
    echo "  - Restart: ./scripts/restart.sh"
}

# Main deployment function
main() {
    echo "=========================================="
    echo "    GreenCode Deployment"
    echo "=========================================="
    
    stop_existing
    create_directories
    deploy_application
    wait_for_startup
    
    if [ $? -eq 0 ]; then
        show_deployment_info
    else
        echo -e "${RED}❌ Deployment failed${NC}"
        exit 1
    fi
}

# Run main function
main "$@"
