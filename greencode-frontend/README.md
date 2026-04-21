# GreenCode Frontend

React frontend for the GreenCode sustainability platform.

## Setup

```bash
cd greencode-frontend
cp .env.example .env
npm install
npm start
```

The app runs at `http://localhost:3000` and connects to the
Spring Boot backend at `http://localhost:8080/api`.

## Pages

| Route              | Description              |
|--------------------|--------------------------|
| `/`                | Dashboard (main page)    |
| `/login`           | Login page               |
| `/forgot-password` | Password reset request   |

## Tech Stack

- React 18
- React Router v6
- Axios (API calls)