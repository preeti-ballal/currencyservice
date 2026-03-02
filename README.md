# Atomic Exchange Service : High-Precision Live Currency Microservice

A real-time currency conversion API built with **Java 17** and **Spring Boot 4.0.3**.
This project demonstrates clean architecture, external API integration, and robust error handling.

## Features
* **Live Market Data:** Integrates with [ExchangeRate-API](https://www.exchangerate-api.com/) to fetch real-time global exchange rates.
* **Intelligent Caching:** Implements a database-first strategy using **Spring Data JPA** and **H2** to reduce API latency and costs.
* **Financial Precision:** Utilizes `BigDecimal` to ensure mathematical accuracy, avoiding the rounding errors associated with floating-point numbers.
* **Global Exception Handling:** Uses `@ControllerAdvice` to provide professional, standardized JSON error responses for invalid requests.
* **Automated Testing:** Comprehensive unit tests using **JUnit 5** and **Mockito** with Deep Stubs for complex `WebClient` mocking.
* **Data Audit:** Every cached rate includes a `lastUpdated` timestamp to track data freshness.

## Tech Stack
* **Java 17** & **Spring Boot 4.x** (Standard Parent 4.0.3)
* **Spring Webflux:** Utilizes `WebClient` for non-blocking external API calls.
* **Spring Data JPA:** For persistence logic and repository abstraction.
* **H2 Database:** In-memory storage for high-speed rate caching.
* **Lombok:** To reduce boilerplate code (Getters, Setters, Constructors).
* **Maven:** For dependency management and build lifecycle.

## API Documentation

### Convert Currency
**Endpoint:** `GET /api/currency/convert`

**Parameters:**
| Parameter | Type | Description | Example |
| :--- | :--- | :--- | :--- |
| `from` | String | Base currency code (ISO 4217) | `USD` |
| `to` | String | Target currency code (ISO 4217) | `EUR` |
| `amount` | Decimal | Amount to be converted | `100.00` |

**Sample Success Response:**
```json
92.45
```

**Sample Error Response:**
```
{
  "timestamp": "2026-03-02T15:56:01",
  "status": 404,
  "error": "Not Found",
  "message": "Live rate not available for USD to XYZ"
}
```

## How to Run

### 1. Prerequisites
* **Java 17** or higher
* **Maven 3.6+**
* An **API Key** from [ExchangeRate-API](https://www.exchangerate-api.com/)

### 2. Configuration
Update `src/main/resources/application.properties` with your credentials:

```properties
# External API Config
api.exchangerate.key=your_api_key_here
api.exchangerate.url=[https://v6.exchangerate-api.com/v6/](https://v6.exchangerate-api.com/v6/)

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### Build & Run

**Force dependency download and build**
```
./mvnw clean install -DskipTests
```

**Run the application**
```
./mvnw spring-boot:run
```
**Execute all tests**
```
./mvnw test
```
