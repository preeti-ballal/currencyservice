# Atomic Exchange Service 

A currency conversion API built with **Java 17** and **Spring Boot 4.0.3**.

## Features
- **Financial Accuracy:** Uses `BigDecimal` with `HALF_UP` rounding to prevent floating-point errors.
- **Clean Architecture:** Separated into Controller, Service, Model and Repository layers.
- **In-Memory Database:** Uses H2 with automated data seeding for immediate testing.
- **RESTful API:** Tested via Postman.

## Tech Stack
- Java 17
- Spring Boot 4
- Spring Data JPA
- H2 Database
- Lombok

## How to Run
1. Clone the repo.
2. Run `./mvnw spring-boot:run`.
3. Open: `http://localhost:8081/api/currency/convert?from=USD&to=JPY&amount=100`
