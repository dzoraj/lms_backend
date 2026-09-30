# lms_backend 🚀

This project is a backend system for a Learning Management System (LMS), built with Spring Boot and Java.

## Description

The `lms_backend` repository contains the server-side logic for a Learning Management System. It is designed to manage various aspects of an educational institution, including universities, faculties, study programs, subjects, users (students, teachers, administrators), and their associated academic activities like course attendance, evaluations, and teaching materials.

## Table of Contents

* [Project Title & Badges](#project-title--badges)
* [Description](#description)
* [Features](#features)
* [Tech Stack](#tech-stack)
* [Installation](#installation)
* [Usage](#usage)
* [API Reference](#api-reference)
* [Project Structure](#project-structure)
* [Contributing](#contributing)
* [License](#license)
* [Important Links](#important-links)
* [Footer](#footer)

## Features ✨

*   **User Management**: Supports different user roles like Students, Teachers, and Administrators, with secure authentication and authorization using JWT.
*   **Course Management**: Manages subjects, course realizations, learning outcomes, and their relationships.
*   **Academic Activities**: Tracks student course attendance, evaluation attempts, and knowledge evaluations.
*   **Educational Content**: Allows for the management of teaching materials and educational goals.
*   **University Structure**: Models universities, faculties, and study programs.
*   **Communication**: Includes a messaging system and WebSocket support for real-time communication.
*   **Forum**: Implements a forum system with topics and posts for discussions.
*   **RESTful APIs**: Provides a comprehensive set of RESTful endpoints for managing all system entities.
*   **CORS Configuration**: Configured to allow requests from `http://localhost:4200`.

## Tech Stack 🛠️

*   **Language**: Java
*   **Framework**: Spring Boot
*   **Database**: MySQL (inferred from `mysql-connector-j` dependency)
*   **Security**: Spring Security, JWT (java-jwt, jjwt)
*   **Websockets**: Spring WebSocket (STOMP)
*   **Build Tool**: Maven

## Installation 💾

1.  **Prerequisites**: Ensure you have Java 24 (or compatible version) and Maven installed.
2.  **Clone the Repository**: 
    ```bash
    git clone https://github.com/dzoraj/lms_backend.git
    cd lms_backend
    ```
3.  **Database Setup**: 
    *   Configure your MySQL database connection by updating the `application.properties` file (or environment variables) with your database credentials.
    *   Ensure the database schema is created (this might require running specific migration scripts, not provided in the analyzed files).
4.  **Build the Project**: 
    ```bash
    mvn clean install
    ```
5.  **Run the Application**: 
    ```bash
    mvn spring-boot:run
    ```
    Alternatively, you can run the `App.java` class directly from your IDE.

## Usage 🧑‍💻

This backend serves as the API for a Learning Management System. Interactions are typically done through REST API calls. Here are some common usage patterns:

### Authentication

*   **Login**: POST `/login` with `UserRequestDTO` (email, password) to receive a JWT token.
    ```json
    {
      "email": "user@example.com",
      "password": "password123"
    }
    ```
    Response includes a token: `{"token": "your.jwt.token"}`

*   **Registration**: POST `/register` with `UserRequestDTO` to create a new user.

### API Endpoints

All endpoints are prefixed with `/api`.

*   **Users**: `/api/user`, `/api/registeredUser`, `/api/student`, `/api/teacher`, `/api/administrator`, `/api/role`
*   **University**: `/api/university`, `/api/faculty`
*   **Subjects**: `/api/subject`, `/api/courseAttendance`, `/api/courseRealization`, `/api/learningOutcome`
*   **Teaching**: `/api/educationalGoal`, `/api/evaluationAttempt`, `/api/evaluationInstrument`, `/api/evaluationType`, `/api/knowledgeEvaluation`, `/api/teacherOnCourse`, `/api/teachingMaterial`, `/api/teachingSession`, `/api/teachingType`
*   **Forum**: `/api/forum`, `/api/post`, `/api/topic`, `/api/userOnForum`
*   **Messages**: `/api/message`
*   **Notifications**: `/api/notification`

**Example: Get all Subjects**

```bash
curl -X GET http://localhost:8080/api/subject
```

**Example: Assign a role to a user**

```bash
curl -X POST http://localhost:8080/api/administrator/assign-role \
-H "Content-Type: application/json" \
-d '{"userId": 1, "roleName": "ADMIN"}'
```

## API Reference 📄

The backend exposes a comprehensive set of RESTful endpoints for managing LMS data. All endpoints are under the `/api` prefix. The base URL is typically `http://localhost:8080` during local development.

Below is a summary of the main API categories:

| Category                 | Base Path                |
| :----------------------- | :----------------------- |
| **Authentication**       | `/login`, `/register`    |
| **User Management**      | `/api/user`, `/api/role`, `/api/administrator`, `/api/registeredUser`, `/api/student`, `/api/teacher` |
| **University Management**| `/api/university`, `/api/faculty` |
| **Study Programs**       | `/api/studyProgram`, `/api/studyYear`, `/api/studentInYear` |
| **Subjects**             | `/api/subject`, `/api/courseAttendance`, `/api/courseRealization`, `/api/learningOutcome` |
| **Teaching**             | `/api/educationalGoal`, `/api/evaluationAttempt`, `/api/evaluationInstrument`, `/api/evaluationType`, `/api/knowledgeEvaluation`, `/api/teacherOnCourse`, `/api/teachingMaterial`, `/api/teachingSession`, `/api/teachingType` |
| **Forum**                | `/api/forum`, `/api/post`, `/api/topic`, `/api/userOnForum` |
| **Messaging**            | `/api/message`           |
| **Notifications**        | `/api/notification`      |
| **Address**              | `/api/address`           |
| **Files**                | `/api/file`              |

*Note: Specific HTTP methods (GET, POST, PUT, DELETE) and request/response DTOs would need to be consulted from the code or generated API documentation (e.g., Swagger/OpenAPI if implemented).* 

## Tech Stack 💻

*   **Java**: Version 24
*   **Spring Boot**: 3.5.0
*   **Spring Security**: For authentication and authorization.
*   **JPA/Hibernate**: For data persistence (inferred from `spring-boot-starter-data-jpa`).
*   **MySQL Connector**: For database interaction.
*   **JWT**: For token-based authentication (`java-jwt`, `jjwt`).
*   **WebSockets**: For real-time features.
*   **Maven**: For dependency management and build.

## Project Structure 📁

The project follows a standard Spring Boot project structure:

```
lms_backend/
├── src/
│   └── main/
│       ├── java/
│       │   └── lmsprojekat/
│       │       ├── config/       # Spring configurations (Security, CORS, WebSocket)
│       │       ├── controller/   # REST Controllers
│       │       │   ├── auth/
│       │       │   ├── forumcontroller/
│       │       │   ├── studentcontroller/
│       │       │   ├── subjectcontroller/
│       │       │   ├── teachingcontroller/
│       │       │   ├── titlecontroller/
│       │       │   ├── universitycontroller/
│       │       │   └── userscontroller/
│       │       ├── dto/          # Data Transfer Objects
│       │       ├── exception/    # Custom exceptions and handlers
│       │       ├── model/        # JPA Entities
│       │       │   ├── forum/
│       │       │   ├── student/
│       │       │   ├── subject/
│       │       │   ├── teaching/
│       │       │   ├── title/
│       │       │   ├── university/
│       │       │   └── users/
│       │       ├── repository/   # Spring Data JPA Repositories
│       │       ├── service/      # Business logic layer
│       │       │   ├── forumservice/
│       │       │   ├── studentservice/
│       │       │   ├── subjectservice/
│       │       │   ├── teachingservice/
│       │       │   ├── titleservice/
│       │       │   ├── universityservice/
│       │       │   └── userservice/
│       │       └── util/         # Utility classes (JWT, etc.)
│       └── resources/
│           └── application.properties # Application configuration
│
├── pom.xml             # Maven project configuration
├── README.md           # Project README file
└── ... (other configuration and build files)
```

## Contributing 📝

Contributions are welcome! Please feel free to submit a Pull Request or open an issue for any improvements or bug fixes.

## License 📄

This project does not specify a license. Please refer to the repository owner for licensing details.

## Important Links 🔗

*   **GitHub Repository**: [https://github.com/dzoraj/lms_backend](https://github.com/dzoraj/lms_backend)

## Footer 📍

© 2023 lms_backend | [GitHub Repository](https://github.com/dzoraj/lms_backend) | Author: d zoraj

Star ⭐ | Fork 🍴 | Watch 👀 | Issue 🐛


---
**<p align="center">Generated by [ReadmeCodeGen](https://www.readmecodegen.com/)</p>**