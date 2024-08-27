package com.crio.learningNavigator.service;

import com.crio.learningNavigator.entity.Exam;
import com.crio.learningNavigator.entity.Student;
import com.crio.learningNavigator.entity.Subject;
import com.crio.learningNavigator.exception.StudentNotFoundException;
import com.crio.learningNavigator.repository.ExamRepository;
import com.crio.learningNavigator.repository.StudentRepository;
import com.crio.learningNavigator.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ExamRepository examRepository;

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long registrationId) throws StudentNotFoundException {
        return studentRepository.findById(registrationId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + registrationId));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Long registrationId, Student student) throws StudentNotFoundException {
        if (!studentRepository.existsById(registrationId)) {
            throw new StudentNotFoundException("Student not found with id " + registrationId);
        }
        student.setRegistrationId(registrationId);
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long registrationId) throws StudentNotFoundException {
        if (!studentRepository.existsById(registrationId)) {
            throw new StudentNotFoundException("Student not found with id " + registrationId);
        }
        studentRepository.deleteById(registrationId);
    }

    @Override
    public void enrollInSubject(Long registrationId, Long subjectId) throws StudentNotFoundException {
        Student student = getStudentById(registrationId);
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with id " + subjectId));
        student.getEnrolledSubjects().add(subject);
        studentRepository.save(student);
    }

    @Override
    public void registerForExam(Long registrationId, Long examId) throws StudentNotFoundException {
        Student student = getStudentById(registrationId);
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with id " + examId));
        student.getRegisteredExams().add(exam);
        studentRepository.save(student);
    }


}
