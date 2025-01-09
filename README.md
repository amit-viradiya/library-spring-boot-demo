Coding Task 1: Build a REST API with Spring Boot
 Create a Spring Boot application to manage a simple library system.
 Requirements:
 1. Endpoints:
 POST /books: Add a new book. Each book should have id, title, author, and price. Validate that the price is non-negative.
GET /books: Retrieve a list of all books.
GET /books/{id}: Retrieve details of a specific book by id. Return a 404 if the book is not found.
PUT /books/{id}: Update an existing book's details. Validate that the price is non-negative. Return a 404 if the book is not found.
DELETE /books/{id}: Delete a book by id. Return a 404 if the book is not found.

2. Additional Requirements:
 Use an in-memory database (like H2) or a Map<Long, Book> for persistence.
Use proper exception handling and return meaningful error messages.
Add a basic unit test for the POST /books and GET /books/{id} endpoints.
---
 Coding Task 2: Kubernetes Configuration
 1. Containerize the above Spring Boot application using Docker. Provide a Dockerfile to build the application image.
2. Write a Kubernetes YAML file to deploy the application:
Create a Deployment with 3 replicas.
Expose the application using a Ingress 
 3. Provide a ConfigMap to inject an environment variable (APP_ENV=production) into the application. Ensure this variable is accessible within the Spring Boot application.
---
Coding Task 3: 
 
Write a method in Java to solve the following problem:
 Given an array of integers, find all pairs of integers whose sum equals a target value.
 Input:
 Array: [2, 4, 3, 7, 1, 5]
Target: 6
 Output: [(2, 4), (3, 3), (1, 5)]
 Optimize for time complexity and avoid duplicate pairs.
