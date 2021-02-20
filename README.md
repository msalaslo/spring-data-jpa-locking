# The Spring Data JPA Locking Example Project

## About

Spring Data JPA Example for test OptimisticLocking and PessimisticLocking by using Annotations. 
Tested with Oracle 12 Database

## Technical Stack:

- Java 1.8+
- Maven 3.6+
- Spring boot 2.4.2.RELEASE
- Oracle for Java Database Driver ojdbc8
- Lombok abstraction
- Mapstruct for bean mapping
- Open API documentation (available at /swagger-ui.html)
- REST API model validation 
- Cucumber and Spring Boot test for test
- Logback for logging
 

## Installation
This application is configured with two SPRING profiles:
- "local": For local dev environments.
- "cloud": For dockerized environments, where application properties are set by ENV variables.

Test on the browser via SWAGGER
-------------------

```sh
http://localhost:8080/locking/swagger-ui.html
```
Performance tests comparison between No locking, Optimistic locking, Pessimistic Read and Pessimistic Write "FIND AND UPDATES"
![GitHub Logo](/images/spring-jpa-locking-performance-tests.jpg)
Format: ![Alt Text](url)