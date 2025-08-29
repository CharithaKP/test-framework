# Rest Assured Framework

A comprehensive REST API testing framework built with Java, REST Assured, and TestNG for automated API testing and validation.

## Overview

This framework provides a robust foundation for API testing with built-in support for HTTP operations, database interactions, configuration management, and response validation. It's designed to simplify the creation and execution of automated API tests.

## Key Features

### 🚀 Core Functionalities

#### 1. **REST API Client**
- **Multi-HTTP Method Support**: GET, POST, PUT, PATCH, DELETE operations
- **Automatic Request/Response Logging**: Comprehensive logging for debugging and monitoring
- **Status Code Validation**: Built-in assertion for expected HTTP status codes
- **JSON Response Handling**: Automatic deserialization to custom response objects
- **Error Handling**: Robust exception handling with detailed error reporting

#### 2. **Configuration Management**
- **Properties-based Configuration**: Centralized configuration through `config.properties`
- **Environment Support**: Multi-environment configuration (stage, production, etc.)
- **Dynamic Property Loading**: Runtime property access via `ConfigManager`
- **Configurable Endpoints**: Base URLs, timeouts, and API headers

#### 3. **Database Integration**
- **Multi-Database Support**: Currently supports MySQL with extensible architecture
- **Connection Management**: Automatic connection handling and cleanup
- **Query Execution**: Support for both SELECT and UPDATE operations
- **Result Mapping**: Automatic mapping of query results to Java objects
- **Database Utilities**: Helper methods for common database operations

#### 4. **Data Transfer Objects (DTOs)**
- **Request DTOs**: Structured request object definitions
- **Response DTOs**: Type-safe response object mapping
- **Lombok Integration**: Reduced boilerplate code with automatic getters/setters

#### 5. **Testing Infrastructure**
- **TestNG Integration**: Powerful test execution and reporting
- **JSON Schema Validation**: Built-in support for response schema validation
- **Logging**: SLF4J-based logging for test execution tracking

## Technology Stack

- **Java 17**: Modern Java features and performance
- **REST Assured 5.4.0**: Industry-standard REST API testing library
- **TestNG 7.9.0**: Flexible test framework with advanced features
- **Lombok 1.18.34**: Code generation for cleaner, more maintainable code
- **Maven**: Dependency management and build automation
- **MySQL**: Database connectivity and testing

## Project Structure

```
src/
├── main/
│   ├── java/in/test/backend/
│   │   ├── base/
│   │   │   ├── RestClient.java          # Core REST API client
│   │   │   ├── RequestType.java         # HTTP method enumeration
│   │   │   └── ServiceURIs.java         # Service endpoint definitions
│   │   ├── clients/                     # API client implementations
│   │   ├── common/
│   │   │   └── ServiceEndpoints.java    # Common endpoint configurations
│   │   ├── dtos/
│   │   │   ├── requestDto/              # Request data transfer objects
│   │   │   └── responseDto/             # Response data transfer objects
│   │   └── utils/
│   │       ├── ConfigManager.java       # Configuration management
│   │       ├── DbUtils.java            # Database utilities
│   │       ├── DBHost.java             # Database host configurations
│   │       ├── RequestBuilders.java    # Request building utilities
│   │       └── ResponseValidators.java # Response validation utilities
│   └── resources/
│       └── config.properties           # Application configuration
└── test/                               # Test implementations
```

## Configuration

### Environment Setup

Configure your testing environment in `src/main/resources/config.properties`:

```properties
# API Configuration
base.url=http://your-api-base-url/
timeout=5000

# Database Configuration
mysql.url=jdbc:mysql://localhost:3306/mydb
mysql.username=testuser
mysql.password=testpass
mysql.driver=com.mysql.cj.jdbc.Driver

# API Headers
Content-Type=application/json
X-App-Token=your-application-token
```

## Usage Examples

### Making API Calls

```java
// Initialize REST client
RestClient restClient = new RestClient();

// Create request specification
RequestSpecification reqSpec = given()
    .baseUri(ConfigManager.get("base.url"))
    .header("Content-Type", "application/json");

// Execute GET request
ResponseDto response = restClient.getResponse(
    RequestType.GET,
    reqSpec,
    ResponseDto.class,
    200
);
```

### Database Operations

```java
// Connect to database
Connection conn = DbUtils.connectToDB(DBHost.MYSQL, "your_database");

// Execute query
List<Map<String, Object>> results = DbUtils.executeQuery("SELECT * FROM users");

// Execute update
int rowsAffected = DbUtils.executeUpdate("UPDATE users SET status = 'active'");

// Close connection
DbUtils.close();
```

### Configuration Access

```java
// Access configuration properties
String baseUrl = ConfigManager.get("base.url");
String dbUrl = ConfigManager.get("mysql.url");
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL database (for database testing features)

### Installation

1. Clone the repository:
```bash
git clone https://github.com/CharithaKP/test-framework.git
cd test-framework
```

2. Install dependencies:
```bash
mvn clean install
```

3. Configure your environment:
   - Update `src/main/resources/config.properties` with your API and database settings

4. Run tests:
```bash
mvn test
```

## Framework Benefits

- **Reusable Components**: Modular design for easy test creation and maintenance
- **Comprehensive Logging**: Detailed request/response logging for debugging
- **Type Safety**: Strong typing with DTOs for request/response handling
- **Error Handling**: Robust error handling and reporting
- **Multi-Environment Support**: Easy configuration switching between environments
- **Database Integration**: Built-in database testing capabilities
- **Extensible Architecture**: Easy to extend for additional functionality

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## License

This project is available under the MIT License.
