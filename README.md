# Job Scraper Microservices Application

This application is designed to track job postings from specific companies and notify users of new openings. The system consists of four microservices: Provider, Explorer, Scraper, and an API Gateway. The front-end is built with Angular, while the backend leverages Spring Boot with a microservice architecture.

## Overview

### Architecture

- **Provider Microservice**: Inputs and stores company names.
- **Explorer Microservice**: Fetches job updates for companies connected to the provider.
- **Scraper Microservice**: Scrapes job updates from LinkedIn and other platforms and sends notifications.
- **API Gateway**: Manages routing, load balancing, and authentication.

### Technologies Used

- **Frontend**: Angular
- **Backend**: Spring Boot (Microservice Architecture)
- **Database**: MySQL (for API Gateway), MongoDB (for Microservices)
- **Messaging**: RabbitMQ (for communication between services)
- **Event Streaming**: Kafka (for job updates and notifications)
- **Monitoring and Logging**: Grafana, Loki, Tempo, and Prometheus
- **Containers**: Docker for managing services in isolated environments

## Microservices Architecture

### 1. API Gateway

- Provides authentication, session management, load balancing, and monitoring.
- Circuit breaker pattern for fault tolerance.

### 2. Provider Microservice

- Provides company information for jobs.
- Connects with the explorer to search for job openings.

### 3. Explorer Microservice

- Consumes job posting events.
- Checks for new job updates from different platforms.

### 4. Scraper Microservice

- Scrapes jobs from LinkedIn and other platforms.
- Integrates with Kafka for producing notifications.

### Monitoring Stack

- **Prometheus**: Metrics collection
- **Grafana**: Data visualization and dashboard
- **Loki**: Log aggregation
- **Tempo**: Distributed tracing

## Key Components

- NgRx for state-management
- Tailwind, Angular Material and PrimeNG for UI
- MySQL: Database for the API Gateway, running on port 3306.
- MongoDB: NoSQL database for microservices, running on port 27017.
- RabbitMQ: Message broker for inter-service communication, running on ports 5672 and 15672.
- Kafka and Zookeeper: Event streaming platform used for sending job update events, -running on ports 9092, 29092 (Kafka) and 2181 (Zookeeper).
- Grafana Stack: For monitoring, logging, and distributed tracing:
- Prometheus (Metrics): Port 9090
- Loki (Logs): Port 3100
- Tempo (Tracing): Port 9411
- Grafana (Visualization): Port 3000

## Setup and Installation

- Clone the repository and navigate to the project directory.
- Run ng serve for frontend
- Set up Docker and Docker Compose on your machine.
- Run the following command to start all services:
- docker-compose up -d
- Access the following services on your browser:
- Grafana: http://localhost:3000
- Kafka UI: http://localhost:8086
- Prometheus: http://localhost:9090
- RabbitMQ: http://localhost:15672

## Contributing

Feel free to raise issues or submit pull requests if you have suggestions for improving the application.
