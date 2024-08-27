Learning Navigator - Exam Enrollment Service
Overview
Learning Navigator is a RESTful API service built with Spring Boot for managing the exam enrollment process in a Learning Management System (LMS). It handles CRUD operations for Students, Subjects, and Exams while ensuring that students can only register for exams if they are enrolled in the corresponding subjects. The service also includes a hidden "Easter egg" feature that generates random facts about numbers using the Numbers API.

Features
CRUD Operations: Manage Students, Subjects, and Exams.
Exam Registration: Students can only register for exams if they are enrolled in the corresponding subject.
Global Exception Handling: Centralized exception handling using @ControllerAdvice.
Hidden Easter Egg Feature: Get random facts about numbers via a hidden endpoint.
Unit Tests: Basic unit tests implemented using JUnit, Mockito, and MockMvc.
Technologies Used
Java 21
Spring Boot
Spring Data JPA
PostgreSQL (for data persistence)
Gradle (for dependency management)
JUnit & Mockito (for testing)
Postman (for API testing)
Getting Started
Prerequisites
Java 21
Gradle 7+
PostgreSQL 15+
Git (for cloning the repository)
Setup and Installation
Clone the repository

bash
Copy code
git clone https://github.com/Sauptik2000/learningNavigator.git
cd learningNavigator
Configure the Database

Create a PostgreSQL database named learning_navigator.
Update the src/main/resources/application.properties file with your PostgreSQL credentials:
properties
Copy code
spring.datasource.url=jdbc:postgresql://localhost:5432/learning_navigator
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
Build the Project

bash
Copy code
./gradlew clean build
Run the Application

bash
Copy code
./gradlew bootRun
The application should start on http://localhost:8080.

API Endpoints
Students
Create a Student

http
Copy code
POST /students
Get Student by ID

http
Copy code
GET /students/{id}
Update Student

http
Copy code
PUT /students/{id}
Delete Student

http
Copy code
DELETE /students/{id}
Subjects
Create a Subject

http
Copy code
POST /subjects
Get Subject by ID

http
Copy code
GET /subjects/{id}
Update Subject

http
Copy code
PUT /subjects/{id}
Delete Subject

http
Copy code
DELETE /subjects/{id}
Exams
Create an Exam

http
Copy code
POST /exams
Get Exam by ID

http
Copy code
GET /exams/{id}
Update Exam

http
Copy code
PUT /exams/{id}
Delete Exam

http
Copy code
DELETE /exams/{id}
Register Student for an Exam

http
Copy code
POST /exams/{examId}/students/{studentId}
Easter Egg Feature
Get a Random Fact about a Number

http
Copy code
GET /hidden-feature/{number}
Note: This endpoint is hidden and not documented in the API documentation.

Testing
Run Unit Tests

bash
Copy code
./gradlew test
API Testing with Postman

Project Structure
/src/main/java/com/learning/navigator
controller: REST controllers for handling HTTP requests.
service: Service classes containing business logic.
repository: JPA repositories for data persistence.
model: Entity classes representing the database structure.
exception: Custom exceptions and global exception handling.
/src/main/resources
application.properties: Configuration file for the Spring Boot application.
Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

License
This project is licensed under the MIT License - see the LICENSE file for details.

This README.md is tailored for your setup using Gradle, PostgreSQL, and Java 21. If you make further changes or add new features, you can update this document accordingly.
