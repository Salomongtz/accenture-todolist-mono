# Todo List API

This is a RESTful API for managing tasks. It allows users to create, retrieve, update, and delete tasks.

## Technologies Used

- Java 11
- Spring Boot 2.5.5
- Swagger UI for API documentation

## Installation

1. Clone the repository: `git clone https://github.com/your-username/todo-list-api.git`
2. Navigate to the project directory: `cd todo-list-api`
3. Build the project: `./mvnw clean package`
4. Run the application: `java -jar target/todo-list-api-0.0.1-SNAPSHOT.jar`

## API Documentation

The API documentation is available at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

## Endpoints

The API provides the following endpoints:

### Create a new task

- **Endpoint**: `POST /task`
- **Description**: Create a new task
- **Request Body**: `application/json`

```json
{
  "title": "Task Title",
  "description": "Task Description",
  "dueDate": "2022-12-31"
}
