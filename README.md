# Api aggregator project

This repo ilustrates the use of Spring Boot to build an API that will act as an API aggregator. This means that the service will contact an external API to retrieve information, transform it into a common model, and make it available to consumers

## Requirements
Favorite IDE

Jdk 21

Maven 3.9.8 (Recommended)

Postman v11.18.0 (Recommended for testing)

## Installation

```bash
git clone https://github.com/GuillermoLeonPy/ContactAPI.git
```

## Usage

```bash
mvn clean compile exec:java -Dspring.profiles.active=dev
```

The resources folder contains a Postman collection file that can be used for testing purposes.
