# ICT-Driven-Agriculture-project
Welcome to the ICT-Driven Agriculture project, part of the Bugema Open Source Community (BOSC). This project empowers smallholder farmers in Uganda and beyond by providing open-source tools for data-driven farming. We focus on collecting soil, weather, and crop data, integrating IoT, supporting farmer decisions with SMS/USSD and mobile dashboards. 

## Project Goals

- Collect and analyze agricultural data (soil, weather, crops).
- Enable IoT integration for sensor-based monitoring.
- Provide farmer-friendly communication via SMS/USSD.
- Offer mobile and web dashboards for real-time insights.

## Tech Stack

| **Purpose**            | **Framework/Tool**                     | **Why We Use It**                                                                 |
|------------------------|---------------------------------------|----------------------------------------------------------------------------------|
| Backend API            | Django or FastAPI (Python)            | Quick setup, excellent for geospatial and agricultural data.                      |
| Mobile App            | React Native or Flutter               | Cross-platform for Android, accessible to farmers.                               |
| GIS/Geo Tools         | GeoDjango, Leaflet.js, OpenLayers     | For farm mapping, soil data, and satellite overlays.                             |
| IoT Integration       | Node-RED, MQTT + Python (Paho)       | Lightweight for sensor data collection and visualization.                        |
| Data Visualization    | Grafana and  Superset                   | Real-time dashboards for weather, crop, and soil conditions.                     |
| SMS/USSD Gateway      | Africa's Talking API, RapidPro, Nexmo | Farmer-friendly communication channels.                                          |

**Cross-Cutting Tools**:
- **GitHub Actions**: CI/CD automation.
- **Docker**: Consistent, reproducible environments.
- **Mapbox**: Interactive mapping.
- **i18n Libraries**: Local language support.

## Setup Instructions

### Prerequisites
- **Git**: For version control.
- **Python 3.8+**: For Django/FastAPI.
- **Node.js**: For Node-RED or React Native.
- **Docker**: For containerized setups.
- **API Keys**: For Africa's Talking, Nexmo, or Mapbox (if used).

### Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/BOSC-Bugema/ict-agriculture.git
   cd ict-agriculture
   ```

2. **Set Up Environment**:
   - Copy `.env.example` to `.env`:
     ```bash
     cp .env.example .env
     ```
   - Update `.env` with API keys (e.g., Africa's Talking, Mapbox) and database credentials.

3. **Choose Your Setup**:
   - **Django Backend**:
     ```bash
     pip install -r requirements.txt
     python manage.py migrate
     python manage.py runserver
     ```
   - **FastAPI Backend**:
     ```bash
     pip install -r requirements.txt
     uvicorn main:app --reload
     ```
   - **React Native Mobile App**:
     ```bash
     cd mobile
     npm install
     npx react-native run-android
     ```
   - **Node-RED for IoT**:
     - Install Node-RED globally: `npm install -g node-red`.
     - Run: `node-red`.
     - Access at `http://localhost:1880`.

4. **Docker Setup (Alternative)**:
   ```bash
   docker-compose up --build
   ```

5. **Configure GIS Tools**:
   - For GeoDjango, ensure PostgreSQL + PostGIS is installed.
   - For Leaflet.js/OpenLayers, include in your frontend:
     ```html
     <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
     ```

6. **Set Up SMS/USSD**:
   - Configure Africa's Talking or Nexmo API in your backend (see `docs/sms_setup.md`).

### Running the Project

- **Backend**: `python manage.py runserver` (Django) or `uvicorn main:app --reload` (FastAPI).
- **Mobile App**: `npx react-native run-android` or `flutter run`.
- **Dashboards**: Run Grafana/Superset locally or via Docker (see `docs/dashboard_setup.md`).
- **IoT**: Access Node-RED at `http://localhost:1880` and configure flows for MQTT sensors.

### Testing

- Run tests: `pytest` (Python) or `npm test` (frontend).
- Check CI/CD pipelines in GitHub Actions for automated testing.

## GitHub Configuration

We follow BOSC’s standards for secure and collaborative development:

- **Security**:
  - Code Scanning: Enabled via GitHub Advanced Security.
  - Secret Scanning: Detects credentials in code.
  - Dependency Review: Blocks vulnerable dependencies.
  - Branch Protection: Requires reviews and CI checks.
  - Audit Logs: Tracks all activity.

- **Workflows**:
  - Template Repositories: Standardized project structure.
  - GitHub Actions: Automates builds, tests, and deployments.
  - GitHub Packages: Stores private Docker images.
  - Collaboration: Clear commit messages (e.g., `feat: add soil data endpoint`) and PR templates.

- **Onboarding**:
  - Check `docs/onboarding.md` for GitHub usage guides.
  - Join our workshops to learn workflows.
  - Use training repos for practice.

- **Monitoring**:
  - Track usage reports and audit logs.
  - Contact support team for issues (see `SUPPORT.md`).

## Contribution Guidelines

- Follow our [commit conventions](docs/CONTRIBUTING.md) (e.g., `fix: resolve API bug`).
- Use PR templates and ensure one approval before merging.
- Optimize for low-bandwidth and offline-first designs.
- Include local language support via i18n.

## License

This project is licensed under the [MIT License](LICENSE).

## Contact

Join us at [https://github.com/BOSC-Bugema](https://github.com/BOSC-Bugema) or email [kmuwanga@bugemauniv.ac.ug](mailto:kmuwanga@bugemauniv.ac.ug).

Let’s grow agriculture together! 🌱 #OpenSource #ICT4Agriculture #BOSC