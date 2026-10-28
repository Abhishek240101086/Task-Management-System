# Task Management System

A full-stack Task Management System built using Java Spring Boot, MySQL, HTML, CSS and JavaScript. The application provides REST APIs for task management along with a simple web interface for performing CRUD operations and filtering tasks by status.

## Tech Stack

### Backend
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Jakarta Bean Validation
- Lombok
- Maven

### Frontend
- HTML
- CSS
- JavaScript

## Features

- Create a new task
- View all tasks
- View a task by ID
- Update existing tasks
- Delete tasks
- Filter tasks by status
- Task priority management
- Task status management
- Due date management
- Input validation
- Global exception handling
- RESTful API integration
- MySQL database persistence

## Task Fields

Each task contains:

- `id` - Unique task ID
- `title` - Task title
- `description` - Task description
- `priority` - LOW, MEDIUM or HIGH
- `status` - PENDING, IN_PROGRESS or COMPLETED
- `dueDate` - Task due date

## REST API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/tasks` | Create a new task |
| GET | `/tasks` | Get all tasks |
| GET | `/tasks/{id}` | Get task by ID |
| GET | `/tasks?status=PENDING` | Filter tasks by status |
| PUT | `/tasks/{id}` | Update a task |
| DELETE | `/tasks/{id}` | Delete a task |

## Validation & Exception Handling

The application validates required task fields such as:

- Title
- Description
- Priority
- Status
- Due Date

Global exception handling is implemented for:

- Task not found
- Invalid request data
- Validation errors

## Database Configuration

The application uses MySQL with Spring Data JPA and Hibernate.

Create a database named:

    CREATE DATABASE taskmanagement;

Database credentials are configured using environment variables:

    spring.datasource.username=${DB_USERNAME}
    spring.datasource.password=${DB_PASSWORD}

## How to Run

### 1. Clone the repository

    git clone https://github.com/Arushi-ydv/taskManagement.git

### 2. Open the project

Open the project in IntelliJ IDEA or any Java IDE.

### 3. Configure MySQL

Make sure MySQL is running and the `taskmanagement` database exists.

Set the following environment variables:

    DB_USERNAME=your_mysql_username
    DB_PASSWORD=your_mysql_password

### 4. Run the application

Using Maven:

    mvn spring-boot:run

Or run:

    TaskManagementApplication.java

### 5. Open the application

Open the following URL in your browser:

    http://localhost:8080

The frontend communicates with the Spring Boot REST APIs to perform task management operations.

## Project Structure

    src
    └── main
        ├── java
        │   └── com.example.taskManagement
        │       ├── controller
        │       ├── entity
        │       ├── enums
        │       ├── exception
        │       ├── repository
        │       └── service
        │
        └── resources
            ├── static
            │   ├── index.html
            │   ├── style.css
            │   └── script.js
            │
            └── application.properties

## Author

**Arushi Yadav**
