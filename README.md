# Java Challenge

## Investment Profit Tax Calculation App

This project aiming to solve Capital Gain challenge from NuBank interview process.

## Prerequisites

- Java 17 or higher
- Windows, macOS, or Linux operating system

### Project Compilation

To compile the project, use the following command:

```bash
./gradlew clean build
```

### Running the Application

Execute the follow command

```bash
java -jar build/libs/investment-profit-1.0-SNAPSHOT.jar
```

then the program will wait a line input in json array format.

```json
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000}, {"operation":"buy", "unit-cost":25.00, "quantity": 5000}, {"operation":"sell", "unit-cost":15.00, "quantity": 10000}]
```
***Warning: Needed to paste one line each time, not able to paste multiples lines on the same time.***


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