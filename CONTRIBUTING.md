# Contributing to GreenCode

Thank you for your interest in contributing to GreenCode! This guide will help you get started with both the backend and the React frontend.

---

## Prerequisites

Before you begin, ensure you have the following installed:

### Backend (Spring Boot)
- Java 17+
- Maven 3.8+
- PostgreSQL 14+
- Docker & Docker Compose (recommended)

### Frontend (React)
- Node.js 18+
- npm 9+ or yarn 1.22+

---

## Setting Up the Frontend

The GreenCode frontend is a React application located in the `greencode-frontend/` directory.

### 1. Clone your fork

```bash
git clone https://github.com/<your-username>/GreenCode.git
cd GreenCode/greencode-frontend
```

### 2. Install dependencies

```bash
npm install
```

### 3. Configure environment variables

Copy the example environment file and update it:

```bash
cp .env.example .env
```

Update `.env` with your backend URL:
### 4. Start the development server

```bash
npm start
```

The app will run at `http://localhost:3000`.

---

## Connecting to the Backend

Make sure the Spring Boot backend is running before starting the frontend. You can start it with:

```bash
# From the project root
docker-compose up -d
```

Or manually:

```bash
mvn spring-boot:run
```

The backend runs at `http://localhost:8080` by default.

---

## Frontend Structure
---

## Git Workflow

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature-name`
3. Make your changes and commit: `git commit -m "feat: describe your change"`
4. Push to your fork: `git push origin feature/your-feature-name`
5. Open a Pull Request to `bos-com/GreenCode` main branch

---

## Code Standards

- Follow React functional component patterns with hooks
- Use Axios for all API calls (configured in `src/services/`)
- Keep components small and reusable
- Write meaningful commit messages using [Conventional Commits](https://www.conventionalcommits.org/)

---

## Reporting Issues

If you find a bug or have a feature request, please open an issue at:
https://github.com/bos-com/GreenCode/issues

---

## Questions?

Reach out via GitHub Discussions or open an issue tagged `question`.
