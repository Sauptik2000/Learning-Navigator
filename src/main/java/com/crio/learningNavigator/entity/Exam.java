package com.crio.learningNavigator.entity;

import jakarta.persistence.*;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@Entity
@Table(name = "exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "subjectId")
    private Subject subject;

    @ManyToMany(mappedBy = "registeredExams")
    private List<Student> enrolledStudents;

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    // No-argument constructor
    public Exam() {
        // No-argument constructor for JPA
    }

    // All-argument constructor
    public Exam(Long examId, Subject subject, List<Student> enrolledStudents) {
        this.examId = examId;
        this.subject = subject;
        this.enrolledStudents = enrolledStudents;
    }

    // Constructor without ID
    public Exam(Subject subject, List<Student> enrolledStudents) {
        this.subject = subject;
        this.enrolledStudents = enrolledStudents;
    }
}
