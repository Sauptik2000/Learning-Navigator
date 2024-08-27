package com.crio.learningNavigator.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;

    private String subjectName;

    @ManyToMany(mappedBy = "enrolledSubjects")
    private List<Student> enrolledStudents;

    @OneToMany(mappedBy = "subject")
    private List<Exam> exams;

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    // No-argument constructor
    public Subject() {
        // No-argument constructor for JPA
    }

    // All-argument constructor
    public Subject(Long subjectId, String subjectName, List<Student> enrolledStudents, List<Exam> exams) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.enrolledStudents = enrolledStudents;
        this.exams = exams;
    }

    // Constructor without ID
    public Subject(String subjectName, List<Student> enrolledStudents, List<Exam> exams) {
        this.subjectName = subjectName;
        this.enrolledStudents = enrolledStudents;
        this.exams = exams;
    }
}
