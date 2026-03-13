# API Automation Framework (Java + Selenium)

## Overview
This framework provides a foundation for API automation using Java, Selenium WebDriver (for UI), and RestAssured (for API). It uses Maven for build management and TestNG for test execution. Logging is handled by Log4j2. Configuration is managed via a properties file.

## Features
- API testing with RestAssured
- UI automation with Selenium WebDriver
- TestNG for test management
- Log4j2 for logging
- Configurable via properties file
- Sample API and UI test classes

## Prerequisites
- Java 11 or higher
- Maven 3.6+

## Setup
1. Clone/download this repository.
2. Run `mvn clean install` to build and download dependencies.
3. Update `src/test/resources/config.properties` as needed.

## Running Tests
- To run all tests: `mvn test`
- To run a specific test: `mvn -Dtest=ClassName test`

## Directory Structure
```
Api-Automation/
├── src/
│   ├── main/java/ (framework code)
│   └── test/java/ (test cases)
│   └── test/resources/ (config, test data)
├── pom.xml
├── README.md
```

## Dependencies
- Selenium WebDriver
- RestAssured
- TestNG
- Log4j2

## Notes
- Replace sample tests with your own as needed.
- Extend the framework for advanced reporting, parallel execution, etc.
