# Spring Boot Application with Clean Architecture
**Overview**
This is a Spring Boot application built using Clean Architecture principles and the Bancolombia plugin. It leverages Spring Cloud Stream with Kafka as the messaging system for event-driven communication.

**Features**
Clean Architecture implementation (layers: domain, application, infrastructure)

**Spring Cloud Stream for event-driven messaging**

**Kafka integration via Spring Kafka Starter**

**Bancolombia plugin for standardized project structure**

**Modular design for maintainability and testability**

**Prerequisites**
Before running the application, ensure you have the following installed:

**Java 17 or higher**

**Maven 3.8.x or higher**

**Docker (for running Kafka locally)**

**Bancolombia plugin (if modifying project structure)**

# Project Structure
```text
src/
├── main/
│   ├── java/
│   │   ├── com/
│   │   │   └── yourcompany/
│   │   │       └── yourproject/
│   │   │           ├── application/       # Application layer (use cases, services)
│   │   │           ├── domain/            # Domain layer (entities, repositories interfaces)
│   │   │           ├── infrastructure/    # Infrastructure layer (implementations, config)
│   │   │           │   ├── input/         # Input adapters (controllers, listeners)
│   │   │           │   ├── output/        # Output adapters (repositories, clients)
│   │   │           │   └── config/        # Configuration classes
│   │   │           └── Application.java   # Main application class
│   ├── resources/
│   │   ├── application.yml                # Main configuration
│   │   └── application-dev.yml            # Dev profile configuration
├── test/                                 # Test directory
```
Kafka Configuration
The application uses Spring Cloud Stream with the following bindings (configured in application.yml):

```yaml
spring:
  cloud:
    stream:
      bindings:
        yourInputChannel:
          destination: your-input-topic
          group: your-consumer-group
        yourOutputChannel:
          destination: your-output-topic
      kafka:
        binder:
          brokers: localhost:9092
```
Running the Application
Local Development
Start Kafka using Docker:
```bash
   docker-compose -f src/main/docker/kafka.yml up -d
```
Build and run the application:

```bash
   mvn clean install
   mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
Production
Package as a JAR and run with production profile:

```bash
   mvn clean package
   java -jar target/your-application.jar --spring.profiles.active=prod
```
Testing
Run unit and integration tests:

```bash
   mvn test
```
Bancolombia Plugin Commands
If you need to modify the project structure using the Bancolombia plugin:

Generate a new use case:

```bash
   bancolombia create use-case --name=YourUseCase --package=com.yourcompany.yourproject
```
Generate a new driven adapter:

```bash
   bancolombia create adapter --type=driven --name=YourRepository --package=com.yourcompany.yourproject
```
Environment Variables
For sensitive configuration, use environment variables:

KAFKA_BROKERS: Kafka broker URLs (comma-separated)

KAFKA_SECURITY_PROTOCOL: Security protocol (e.g., SASL_SSL)

KAFKA_SASL_*: SASL authentication credentials

Contributing
Fork the repository

Create your feature branch (git checkout -b feature/your-feature)

Commit your changes (git commit -am 'Add some feature')

Push to the branch (git push origin feature/your-feature)

Create a new Pull Request
