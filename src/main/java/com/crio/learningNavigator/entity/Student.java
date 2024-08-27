package com.crio.learningNavigator.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registrationId;

    private String studentName;

    @ManyToMany
    @JoinTable(name = "student_subject",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "registrationId"),
            inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "subjectId"))
    private List<Subject> enrolledSubjects;

    @ManyToMany
    @JoinTable(name = "student_exam",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "registrationId"),
            inverseJoinColumns = @JoinColumn(name = "exam_id", referencedColumnName = "examId"))
    private List<Exam> registeredExams;

    public Long getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Long registrationId) {
        this.registrationId = registrationId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<Subject> getEnrolledSubjects() {
        return enrolledSubjects;
    }

    public void setEnrolledSubjects(List<Subject> enrolledSubjects) {
        this.enrolledSubjects = enrolledSubjects;
    }

    public List<Exam> getRegisteredExams() {
        return registeredExams;
    }

    public void setRegisteredExams(List<Exam> registeredExams) {
        this.registeredExams = registeredExams;
    }

    // No-argument constructor
    public Student() {
        // No-argument constructor for JPA
    }

    // All-argument constructor
    public Student(Long registrationId, String studentName, List<Subject> enrolledSubjects, List<Exam> registeredExams) {
        this.registrationId = registrationId;
        this.studentName = studentName;
        this.enrolledSubjects = enrolledSubjects;
        this.registeredExams = registeredExams;
    }

    // Constructor without ID
    public Student(String studentName, List<Subject> enrolledSubjects, List<Exam> registeredExams) {
        this.studentName = studentName;
        this.enrolledSubjects = enrolledSubjects;
        this.registeredExams = registeredExams;
    }
}
