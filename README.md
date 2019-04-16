# Getting Started

## Domain

The domain is to find best route by lower price given two points (GRU-CDG) and to save new non-existent routes to the database.

## Domain Validation

There are some validations like **mandatory fields** and **input pattern**

- For example if you input a **source and destination** (**info route**) with underscore for example **"GRU_CDG"**, it will return an error saying that info route is invalid and an example showing that **GRU-CDG** pattern is the correct. Use hyphen instead of underscore.

- If you input a **info route** which **doesn't exist** when fetching by best route, it will return "Route was not found with **GRX-CSD**" for example.

## Database

Database is using **CSV** format and is located in **db** directory in root project.

This project has two communication endpoints, one through console and another through rest endpoint.

## Console 

Input a source and destination in console for example 'GRU-CDG' to find best route by lower price.

## Rest Endpoints

All URL's endpoints are located in **RestContract** class in **br.carloskafka.desafiotecnico.services.rest** package.

```
1. POST /route - Add new route

2. GET /route/best-route/{inforoute} - fetch best route using route's information (GRU-CDG)

```

# Running Integration Tests

The project also contains integration tests to all API's endpoints. To execute integration test, you need to navigate to the project's root directory and type `mvn test` in terminal (windows or linux).

Run command `mvn test`

# Running application 
Execute application through `mvn package` and `java -jar target\desafiotecnico2-0.0.1-SNAPSHOT.jar console-database.csv`
