# Docker Setup Guide

This guide explains how to run GreenCode using Docker and Docker Compose.

## Prerequisites

- Docker installed
- Docker Compose installed
- Git installed

## Start the Application

```bash
docker-compose up -d2. In your fork, create this file

Go to:


Create file:

docs/docker-setup.md


Check Running Services
docker-compose ps
View Logs
docker-compose logs -f

To view backend logs only:

docker-compose logs -f greencode-backend
Stop Services
docker-compose down
Rebuild Containers
docker-compose up -d --build
Common Issues
Port Already in Use

If port 8080 or 5432 is already used, stop the other service or change the mapped port in docker-compose.yml.

Database Connection Error

Ensure PostgreSQL is running:

docker-compose ps

Then restart services:

docker-compose restart
Notes
Use .env.example as a guide when creating your .env file.
Do not commit real passwords or secrets.
Check container logs when debugging errors.

Commit message:

```text
Add Docker setup guide
