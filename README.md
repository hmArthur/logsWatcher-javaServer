# HttpServer for Watching Deploy information

A server built from scratch in Java to handle and monitor deploy data.  
This project manually implements core backend concepts like HTTP routing and logging, i'm actually using it to 
learn more about the core of back-end without the use of frameworks like spring.

## Features
- Receive and Retrieve deployment data via HTTP 
- Basic logging of requests and responses

## Getting Started
### requisites
- Java 11 or higher
- 
### How to Run
  -if you're in linux you can just run
   ``./run.sh``
  -in windows just use:
  ``javac -cp lib/gson-2.13.1.jar -d out $(find src -name "*.java")
    java -cp "out:lib/gson-2.13.1.jar" App <port>``

## Project Structure
- `App.java` — Server entry point
- `DeployHandler.java` — HTTP request handler for deploy endpoints
- `DeployService.java` — Business logic for deploys
- `JsonManager.java` — JSON file read/write utility
- `Log.java` & `LogWriter.java` — Request logging utilities

## Things i still expect to implement
  - basic cache sysyem
  - api key authentication
  - basic Cryptography
  - log manipulation to generate alerts using bash, maybe emails or discord alerts?
