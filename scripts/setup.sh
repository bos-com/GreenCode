#!/bin/bash

# GreenCode Development Setup Script
# This script sets up the development environment for GreenCode backend

set -e

echo "🚀 Setting up GreenCode Development Environment..."

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Check if Java is installed
check_java() {
    echo "📋 Checking Java installation..."
    if command -v java &> /dev/null; then
        JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2)
        echo -e "${GREEN}✅ Java found: $JAVA_VERSION${NC}"
        
        # Check if Java 17 or higher
        if [[ "$JAVA_VERSION" == 17* ]] || [[ "$JAVA_VERSION" == 18* ]] || [[ "$JAVA_VERSION" == 19* ]] || [[ "$JAVA_VERSION" == 20* ]] || [[ "$JAVA_VERSION" == 21* ]]; then
            echo -e "${GREEN}✅ Java version is compatible${NC}"
        else
            echo -e "${RED}❌ Java version $JAVA_VERSION is not compatible. Please install Java 17 or higher.${NC}"
            exit 1
        fi
    else
        echo -e "${RED}❌ Java is not installed. Please install Java 17 or higher.${NC}"
        exit 1
    fi
}

# Check if Maven is installed
check_maven() {
    echo "📋 Checking Maven installation..."
    if command -v mvn &> /dev/null; then
        MAVEN_VERSION=$(mvn -version | head -n 1 | cut -d' ' -f3)
        echo -e "${GREEN}✅ Maven found: $MAVEN_VERSION${NC}"
    else
        echo -e "${RED}❌ Maven is not installed. Please install Maven 3.6 or higher.${NC}"
        exit 1
    fi
}

# Check if Docker is installed
check_docker() {
    echo "📋 Checking Docker installation..."
    if command -v docker &> /dev/null; then
        DOCKER_VERSION=$(docker --version | cut -d' ' -f3 | cut -d',' -f1)
        echo -e "${GREEN}✅ Docker found: $DOCKER_VERSION${NC}"
    else
        echo -e "${YELLOW}⚠️  Docker is not installed. Some features may not work.${NC}"
    fi
}

# Create necessary directories
create_directories() {
    echo "📁 Creating project directories..."
    mkdir -p logs
    mkdir -p data
    mkdir -p reports
    mkdir -p config/nginx
    mkdir -p config/docker
    echo -e "${GREEN}✅ Directories created${NC}"
}

# Copy environment file
setup_environment() {
    echo "🔧 Setting up environment configuration..."
    if [ ! -f .env ]; then
        if [ -f .env.example ]; then
            cp .env.example .env
            echo -e "${GREEN}✅ Environment file created from template${NC}"
            echo -e "${YELLOW}⚠️  Please review and update .env file with your configuration${NC}"
        else
            echo -e "${YELLOW}⚠️  .env.example not found. Please create .env file manually.${NC}"
        fi
    else
        echo -e "${GREEN}✅ Environment file already exists${NC}"
    fi
}

# Build the project
build_project() {
    echo "🔨 Building the project..."
    mvn clean install -DskipTests
    echo -e "${GREEN}✅ Project built successfully${NC}"
}

# Run tests
run_tests() {
    echo "🧪 Running tests..."
    mvn test
    echo -e "${GREEN}✅ Tests completed${NC}"
}

# Start the application
start_application() {
    echo "🚀 Starting GreenCode application..."
    echo -e "${YELLOW}⚠️  Application will start in background. Check logs/ directory for output.${NC}"
    nohup mvn spring-boot:run > logs/application.log 2>&1 &
    echo $! > .pid
    echo -e "${GREEN}✅ Application started with PID $(cat .pid)${NC}"
    echo -e "${GREEN}🌐 Access the application at: http://localhost:8080${NC}"
    echo -e "${GREEN}📚 Swagger UI at: http://localhost:8080/api/swagger-ui.html${NC}"
    echo -e "${GREEN}🗄️  H2 Console at: http://localhost:8080/api/h2-console${NC}"
}

# Main setup function
main() {
    echo "=========================================="
    echo "    GreenCode Development Setup"
    echo "=========================================="
    
    check_java
    check_maven
    check_docker
    create_directories
    setup_environment
    build_project
    run_tests
    
    echo ""
    echo -e "${GREEN}🎉 Setup completed successfully!${NC}"
    echo ""
    echo "Next steps:"
    echo "1. Review and update .env file if needed"
   echo "2. Run: ./scripts/start.sh to start the application, or call start_application if using this script interactively"
   echo "2. Run: ./scripts/start.sh to start the application"
   echo "   Note: setup.sh prepares the environment but does not start the app automatically."
    echo "3. Run: ./scripts/stop.sh to stop the application"
    echo "4. Check logs/ directory for application logs"
}

# Run main function
main "$@"
