## Getting Started

### Prerequisites
Make sure you have the following installed before running the project:

- Java 17+
- Node.js 18+
- Docker & Docker Compose
- PostgreSQL 14+

### Backend Setup
1. Clone the repository
```bash
   git clone https://github.com/your-org/greencode.git
   cd greencode
```
2. Copy the environment file and fill in your values
```bash
   cp .env.example .env
```
3. Run the backend with Docker
```bash
   docker-compose up --build
```
4. The API will be available at `http://localhost:8080`
5. Swagger docs at `http://localhost:8080/swagger-ui.html`

### Frontend Setup
1. Navigate to the frontend folder
```bash
   cd greencode-frontend
```
2. Install dependencies
```bash
   npm install
```
3. Start the development server
```bash
   npm start
```
4. The app will be available at `http://localhost:3000`

### Environment Variables
Create a `.env` file in the root directory based on `.env.example`. Key variables include:

| Variable | Description |
|---|---|
| `DB_URL` | PostgreSQL connection URL |
| `DB_USERNAME` | Database username |
| `DB_PASSWORD` | Database password |
| `JWT_SECRET` | Secret key for JWT tokens |
