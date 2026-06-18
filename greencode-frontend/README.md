# GreenCode Frontend

Modern React frontend for the GreenCode environmental project management platform.

## Features

- **Authentication**: Login, registration, and JWT token management
- **Dashboard**: Overview of projects and environmental impact metrics
- **Project Management**: View, create, and manage environmental projects
- **User Profile**: Manage personal information and account settings
- **Responsive Design**: Mobile-friendly interface using Tailwind CSS
- **TypeScript**: Type-safe development experience

## Tech Stack

- **React 18** with TypeScript
- **React Router** for navigation
- **Tailwind CSS** for styling
- **Axios** for API communication
- **React Query** for data fetching
- **Heroicons** for icons
- **React Hook Form** for form management

## Getting Started

### Prerequisites

- Node.js 16+ and npm
- Backend API running on http://localhost:8080

### Installation

1. Clone the repository:
```bash
git clone https://github.com/MarlonPiusMulamba/GreenCode.git
cd GreenCode/greencode-frontend
```

2. Install dependencies:
```bash
npm install
```

3. Create environment file:
```bash
cp .env.example .env
```

4. Start the development server:
```bash
npm start
```

The application will be available at http://localhost:3000

## Available Scripts

- `npm start` - Runs the app in development mode
- `npm build` - Builds the app for production
- `npm test` - Launches the test runner
- `npm run eject` - Ejects from Create React App (one-way operation)

## Project Structure

```
src/
├── components/     # Reusable UI components
├── contexts/       # React contexts (Auth, etc.)
├── pages/          # Page components
├── services/       # API service functions
├── types/          # TypeScript type definitions
├── App.tsx         # Main app component
├── index.tsx       # Entry point
└── index.css       # Global styles
```

## API Integration

The frontend communicates with the backend API through the following endpoints:

- Authentication: `/api/auth/*`
- Users: `/api/users/*`
- Projects: `/api/projects/*`

## Environment Variables

- `REACT_APP_API_URL`: Backend API base URL (default: http://localhost:8080/api)

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
