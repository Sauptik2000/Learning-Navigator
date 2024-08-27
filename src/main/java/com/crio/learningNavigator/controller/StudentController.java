package com.crio.learningNavigator.controller;

import com.crio.learningNavigator.entity.Student;
import com.crio.learningNavigator.exception.StudentNotFoundException;
import com.crio.learningNavigator.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("/{registrationId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long registrationId) {
        try {
            Student student = studentService.getStudentById(registrationId);
            return ResponseEntity.ok(student);
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @PutMapping("/{registrationId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long registrationId, @RequestBody Student student) {
        try {
            Student updatedStudent = studentService.updateStudent(registrationId, student);
            return ResponseEntity.ok(updatedStudent);
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/{registrationId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long registrationId) {
        try {
            studentService.deleteStudent(registrationId);
            return ResponseEntity.noContent().build();
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/{registrationId}/subjects/{subjectId}")
    public ResponseEntity<Void> enrollInSubject(@PathVariable Long registrationId, @PathVariable Long subjectId) {
        try {
            studentService.enrollInSubject(registrationId, subjectId);
            return ResponseEntity.noContent().build();
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/{registrationId}/exams/{examId}")
    public ResponseEntity<Void> registerForExam(@PathVariable Long registrationId, @PathVariable Long examId) {
        try {
            studentService.registerForExam(registrationId, examId);
            return ResponseEntity.noContent().build();
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(404).build();
        }
    }
}