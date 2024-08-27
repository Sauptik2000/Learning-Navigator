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
git clone https://github.com/Sauptik2000/learningNavigator.git
cd learningNavigator

* Configure PostgreSQL: Update application.properties with your DB credentials.

* Build and Run:
./gradlew bootRun

API Endpoints:
1. Students
    POST /students
    GET /students/{id}
    PUT /students/{id}
    DELETE /students/{id}
2. Subjects
    POST /subjects
    GET /subjects/{id}
    PUT /subjects/{id}
    DELETE /subjects/{id}
3. Exams
    POST /exams
    GET /exams/{id}
    PUT /exams/{id}
    DELETE /exams/{id}
    POST /exams/{examId}/students/{studentId}
4. Easter Egg Feature
    Get a Random Fact about a Number:- GET /hidden-feature/{number}
