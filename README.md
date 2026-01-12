🚀 Spring Boot Microservices with Gradle, Docker & Docker Compose
This repository demonstrates a production-oriented microservices architecture built using Spring Boot, Gradle, Docker, and Docker Compose.
The goal of this project is to learn and implement microservices the right way — focusing not only on functionality, but also on containerization, resource management, health checks, and orchestration.
📌 Architecture Overview
The system consists of three independent microservices:
Service	Description	Port
Accounts	Manages account-related APIs	8085
Cards	Manages card-related APIs	9000
Loans	Manages loan-related APIs	8090
Each service:
Is independently deployable
Has its own Gradle build
Is packaged as a Docker image
Exposes health endpoints
Runs in a shared Docker network
🧱 Tech Stack
Java 17
Spring Boot
Spring Data JPA
Gradle
Docker
Docker Compose
H2 (in-memory DB for now)
Spring Boot Actuator
📂 Project Structure
Springboot-microservices/
│
├── accounts/
│   ├── src/
│   ├── build.gradle
│
├── cards/
│   ├── src/
│   ├── build.gradle
│
├── loans/
│   ├── src/
│   ├── build.gradle
│
├── docker-compose.yml
└── README.md
Each microservice is a standalone Spring Boot application with its own lifecycle.
🐳 Docker & Image Management
Docker Images
Each microservice is containerized as a Docker image:
accounts-service:1.0
cards-service:1.0
loans-service:1.0
Images are built using Gradle (bmuschko docker plugin) — no manual Dockerfiles are required.
Build Docker Images
From each service directory:
./gradlew dockerBuildImage
🧩 Docker Compose Orchestration
All services are orchestrated using Docker Compose, which:
Creates a shared bridge network
Starts all services together
Applies memory limits
Performs health checks
Enables service discovery via container names
Start all services
From the root directory:
docker-compose up -d
Stop all services
docker-compose down
🌐 Networking & Service Discovery
All services run on a shared Docker network.
Inside the Docker network, services can communicate using service names:
http://accounts:8085
http://cards:9000
http://loans:8090
No hardcoded IPs or localhost usage is required for inter-service communication.
❤️ Health Checks (Cloud-Native Ready)
Each service exposes Spring Boot Actuator health endpoints:
/actuator/health
/actuator/health/liveness
/actuator/health/readiness
Example:
curl http://localhost:8085/actuator/health
Response:
{
  "status": "UP",
  "groups": ["liveness", "readiness"]
}
These endpoints make the services Docker & Kubernetes ready.
🧠 Resource Management
Each container is configured with:
Memory limits
JVM heap tuning
Example:
deploy:
  resources:
    limits:
      memory: 512M

environment:
  JAVA_TOOL_OPTIONS: "-Xms256m -Xmx384m"
This ensures:
No service can exhaust system memory
Predictable and stable runtime behavior
🎯 Key Learnings & Objectives
This project focuses on:
Designing independent microservices
Using Gradle for builds and automation
Containerizing applications using Docker
Orchestrating services using Docker Compose
Applying health checks & resource limits
Building cloud-native, production-ready systems
🔜 Future Enhancements
Planned next steps:
Inter-service communication (Feign / RestTemplate)
External databases (MySQL / PostgreSQL via Docker)
Spring Profiles (local, docker, prod)
Centralized configuration (Spring Cloud Config)
API Gateway
Kubernetes deployment manifests
🏁 Conclusion
This repository is not just a demo — it is a step-by-step evolution toward a real-world microservices platform.
It emphasizes architecture, correctness, and best practices over shortcuts.
