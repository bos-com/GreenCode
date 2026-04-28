# Contributing to GreenCode

Thank you for your interest in contributing to GreenCode! This document provides guidelines and information for contributors.

## Table of Contents

- [Getting Started](#getting-started)
- [Development Setup](#development-setup)
- [How to Contribute](#how-to-contribute)
- [Code Guidelines](#code-guidelines)
- [Testing](#testing)
- [Documentation](#documentation)
- [Pull Request Process](#pull-request-process)
- [Community](#community)

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- Node.js 16+ and npm (for frontend)
- Docker and Docker Compose (for containerized development)
- Git

### Development Setup

1. **Fork the repository**
   ```bash
   # Fork the repository on GitHub and clone your fork
   git clone https://github.com/YOUR_USERNAME/GreenCode.git
   cd GreenCode
   ```

2. **Add upstream remote**
   ```bash
   git remote add upstream https://github.com/MarlonPiusMulamba/GreenCode.git
   ```

3. **Backend Setup**
   ```bash
   # Navigate to backend directory
   cd src/main/java/com/greencode
   
   # Run the application
   mvn spring-boot:run
   ```

4. **Frontend Setup**
   ```bash
   # Navigate to frontend directory
   cd greencode-frontend
   
   # Install dependencies
   npm install
   
   # Start development server
   npm start
   ```

5. **Database Setup**
   ```bash
   # Using Docker Compose
   docker-compose up -d postgres redis
   ```

## How to Contribute

### Types of Contributions

We welcome the following types of contributions:

1. **Bug Fixes**: Report and fix bugs
2. **Feature Enhancements**: Add new features
3. **Documentation**: Improve documentation
4. **UI/UX Improvements**: Enhance user interface and experience
5. **Performance**: Optimize application performance
6. **Security**: Identify and fix security vulnerabilities

### Reporting Issues

1. **Search existing issues** before creating a new one
2. **Use appropriate labels** (bug, enhancement, documentation, etc.)
3. **Provide detailed information**:
   - Clear description of the issue
   - Steps to reproduce
   - Expected vs actual behavior
   - Environment details (OS, browser, version)
   - Screenshots if applicable

### Feature Requests

1. **Check roadmap** to see if the feature is planned
2. **Create an issue** with the "enhancement" label
3. **Provide detailed description**:
   - Problem statement
   - Proposed solution
   - Use cases and benefits
   - Implementation ideas (optional)

## Code Guidelines

### Backend (Java/Spring Boot)

1. **Follow Java conventions**
   - Use camelCase for variables and methods
   - Use PascalCase for classes and interfaces
   - Use UPPER_SNAKE_CASE for constants

2. **Code structure**
   ```java
   package com.greencode.service;
   
   import org.springframework.stereotype.Service;
   
   @Service
   public class ExampleService {
       // Private fields
       private final ExampleRepository repository;
       
       // Constructor injection
       public ExampleService(ExampleRepository repository) {
           this.repository = repository;
       }
       
       // Public methods
       public ExampleEntity createExample(ExampleDto dto) {
           // Implementation
       }
   }
   ```

3. **Error handling**
   - Use custom exceptions
   - Provide meaningful error messages
   - Log errors appropriately

4. **Security**
   - Validate all inputs
   - Use parameterized queries
   - Follow OWASP guidelines

### Frontend (React/TypeScript)

1. **Component structure**
   ```typescript
   import React from 'react';
   
   interface ComponentProps {
     title: string;
     onAction: () => void;
   }
   
   const Component: React.FC<ComponentProps> = ({ title, onAction }) => {
     return (
       <div>
         <h1>{title}</h1>
         <button onClick={onAction}>Action</button>
       </div>
     );
   };
   
   export default Component;
   ```

2. **TypeScript best practices**
   - Use interfaces for props
   - Avoid `any` type
   - Use proper typing for API responses

3. **Styling**
   - Use Tailwind CSS classes
   - Follow responsive design principles
   - Maintain consistent design system

## Testing

### Backend Testing

1. **Unit tests** for services and repositories
   ```java
   @ExtendWith(MockitoExtension.class)
   class ExampleServiceTest {
       @Mock
       private ExampleRepository repository;
       
       @InjectMocks
       private ExampleService service;
       
       @Test
       void shouldCreateExample() {
           // Test implementation
       }
   }
   ```

2. **Integration tests** for controllers
   ```java
   @SpringBootTest
   @AutoConfigureTestDatabase
   class ExampleControllerTest {
       @Autowired
       private TestRestTemplate restTemplate;
       
       @Test
       void shouldReturnExample() {
           // Test implementation
       }
   }
   ```

### Frontend Testing

1. **Component tests** using React Testing Library
2. **Integration tests** for user flows
3. **E2E tests** for critical user journeys

## Documentation

### Code Documentation

1. **JavaDoc** for all public APIs
2. **Inline comments** for complex logic
3. **README** updates for new features

### API Documentation

1. **OpenAPI/Swagger** annotations for all endpoints
2. **Request/response examples**
3. **Error response documentation**

### User Documentation

1. **User guide** updates
2. **Installation instructions**
3. **Troubleshooting guides**

## Pull Request Process

### Before Submitting

1. **Create a feature branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. **Make your changes**
   - Follow code guidelines
   - Add tests
   - Update documentation

3. **Test your changes**
   ```bash
   # Backend tests
   mvn test
   
   # Frontend tests
   npm test
   ```

4. **Commit your changes**
   ```bash
   git add .
   git commit -m "feat: add new feature description"
   ```

### Pull Request Guidelines

1. **Use descriptive title** following conventional commits:
   - `feat:` for new features
   - `fix:` for bug fixes
   - `docs:` for documentation
   - `style:` for formatting
   - `refactor:` for code refactoring
   - `test:` for adding tests
   - `chore:` for maintenance

2. **Provide detailed description**:
   - What was changed and why
   - How to test the changes
   - Any breaking changes
   - Related issues

3. **Add screenshots** for UI changes

4. **Link related issues** using GitHub keywords

### Review Process

1. **Automated checks** must pass
2. **Code review** by maintainers
3. **Testing** by reviewers
4. **Approval** required before merge

## Community

### Communication Channels

- **GitHub Issues**: Bug reports and feature requests
- **GitHub Discussions**: General questions and ideas
- **Discord**: Real-time community chat

### Getting Help

1. **Check documentation** first
2. **Search existing issues**
3. **Ask questions in discussions**
4. **Join our Discord community**

### Recognition

Contributors are recognized through:
- **Contributor list** in README
- **Release notes** credits
- **Community spotlight** in newsletter
- **Maintainer consideration** for active contributors

## License

By contributing to GreenCode, you agree that your contributions will be licensed under the MIT License.

## Questions?

If you have any questions about contributing, please:

1. Check our [FAQ](docs/FAQ.md)
2. Search existing [discussions](https://github.com/MarlonPiusMulamba/GreenCode/discussions)
3. Create a new discussion with the "question" label

Thank you for contributing to GreenCode and helping make environmental project management better for everyone! 🌱
