**Learning Navigator - Exam Enrollment Service**

Overview: 
Learning Navigator is a RESTful API built with Spring Boot to manage exam enrollment in a Learning Management System (LMS). It supports CRUD operations for Students, Subjects, and Exams and includes an Easter egg feature that returns random facts about numbers.

Key Features:
1.Manage Students, Subjects, and Exams
2.Register Students for Exams (after subject enrollment)
3.Centralized Exception Handling
4.Easter Egg Feature: Get random number facts from the Numbers API

Technologies Used:
1.Java 21
2.Spring Boot
3.PostgreSQL
4.Gradle
5.JUnit & Mockito

Getting Started: 

1.Prerequisites-
Java 21, Gradle, PostgreSQL

2.Setup
* Clone the repo:
  1. git clone https://github.com/Sauptik2000/learningNavigator.git
  2. cd learningNavigator

* Configure PostgreSQL:
  1. Update application.properties with your DB credentials.

* Build and Run:
  1. ./gradlew bootRun

API Endpoints:
1. Students
    1. POST /students
    2. GET /students/{id}
    3. PUT /students/{id}
    4. DELETE /students/{id}
2. Subjects
    1. POST /subjects
    2. GET /subjects/{id}
    3. PUT /subjects/{id}
    4. DELETE /subjects/{id}
3. Exams
    1. POST /exams
    2. GET /exams/{id}
    3. PUT /exams/{id}
    4. DELETE /exams/{id}
    5. POST /exams/{examId}/students/{studentId}
4. Easter Egg Feature
    1. Get a Random Fact about a Number:- GET /hidden-feature/{number}
