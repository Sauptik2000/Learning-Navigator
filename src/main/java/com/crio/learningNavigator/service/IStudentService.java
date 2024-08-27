package com.crio.learningNavigator.service;

import com.crio.learningNavigator.entity.Student;
import com.crio.learningNavigator.exception.StudentNotFoundException;

import java.util.List;

public interface IStudentService {
    Student createStudent(Student student);
    Student getStudentById(Long registrationId) throws StudentNotFoundException;
    List<Student> getAllStudents();
    Student updateStudent(Long registrationId, Student student) throws StudentNotFoundException;
    void deleteStudent(Long registrationId) throws StudentNotFoundException;
    void enrollInSubject(Long registrationId, Long subjectId) throws StudentNotFoundException;
    void registerForExam(Long registrationId, Long examId) throws StudentNotFoundException;
}