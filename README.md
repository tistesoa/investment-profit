# Java Challenge

## Investment Profit Tax Calculation App

This project aiming to solve Capital Gain challenge interview process.

I structured my files using Domain-driven Design principles, which involve separating domain-specific code from application logic. This approach enhances readability and facilitates future enhancements to the application.

I adhere to the principles of Single Responsibility, Open/Closed, and Interface Segregation to ensure that each component has a clear and focused purpose. This design allows for easier extension and modification of tax calculation logic, accommodating new tax types or rates as needed.

I consistently apply the principles of KISS (Keep It Simple, Stupid), DRY (Don't Repeat Yourself), and YAGNI (You Aren't Gonna Need It) to maintain code simplicity, cleanliness, and readability."
## Prerequisites

- Java 17 or higher
- Windows, macOS, or Linux operating system

### Project Compilation

To compile the project, use the following command:

```bash
./gradlew clean build
```
### Running unit tests
```bash
./gradlew test
```
## Running the Application

Execute the follow command

```bash
java -jar build/libs/investment-profit-1.0-SNAPSHOT.jar
```

then the program will wait a line input in json array format.

```json
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000}, {"operation":"buy", "unit-cost":25.00, "quantity": 5000}, {"operation":"sell", "unit-cost":15.00, "quantity": 10000}]
```

You can either input a json file like

```bash
java -jar build/libs/investment-profit-1.0-SNAPSHOT.jar < input.json
```
where the input.json is like:
```json
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000}, {"operation":"buy", "unit-cost":25.00, "quantity": 5000}, {"operation":"sell", "unit-cost":15.00, "quantity": 10000}]
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000}, {"operation":"sell", "unit-cost":20.00, "quantity": 5000}, {"operation":"sell", "unit-cost":5.00, "quantity": 5000}]
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000}, {"operation":"sell", "unit-cost":5.00, "quantity": 5000}, {"operation":"sell", "unit-cost":20.00, "quantity": 3000}]
```

**Finally, press enter to exit the program.**
