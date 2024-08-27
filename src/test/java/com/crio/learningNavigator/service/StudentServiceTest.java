package com.crio.learningNavigator.service;

import com.crio.learningNavigator.entity.Exam;
import com.crio.learningNavigator.entity.Student;
import com.crio.learningNavigator.entity.Subject;
import com.crio.learningNavigator.exception.StudentNotFoundException;
import com.crio.learningNavigator.repository.ExamRepository;
import com.crio.learningNavigator.repository.StudentRepository;
import com.crio.learningNavigator.repository.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private ExamRepository examRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStudent() {
        Student student = new Student();
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student createdStudent = studentService.createStudent(student);
        assertNotNull(createdStudent);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testGetStudentById() throws StudentNotFoundException {
        Student student = new Student();
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        Student retrievedStudent = studentService.getStudentById(1L);
        assertNotNull(retrievedStudent);
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void testGetStudentByIdNotFound() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> studentService.getStudentById(1L));
    }

    @Test
    void testGetAllStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student());
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> retrievedStudents = studentService.getAllStudents();
        assertNotNull(retrievedStudents);
        assertEquals(1, retrievedStudents.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testUpdateStudent() throws StudentNotFoundException {
        Student existingStudent = new Student();
        Student updatedStudent = new Student();
        when(studentRepository.existsById(anyLong())).thenReturn(true);
        when(studentRepository.save(any(Student.class))).thenReturn(updatedStudent);

        Student result = studentService.updateStudent(1L, updatedStudent);
        assertNotNull(result);
        verify(studentRepository, times(1)).save(updatedStudent);
    }

    @Test
    void testUpdateStudentNotFound() {
        when(studentRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(StudentNotFoundException.class, () -> studentService.updateStudent(1L, new Student()));
    }

    @Test
    void testDeleteStudent() throws StudentNotFoundException {
        when(studentRepository.existsById(anyLong())).thenReturn(true);

        studentService.deleteStudent(1L);
        verify(studentRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteStudentNotFound() {
        when(studentRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(StudentNotFoundException.class, () -> studentService.deleteStudent(1L));
    }

    @Test
    void testEnrollInSubject() throws StudentNotFoundException {
        Student student = new Student();
        student.setEnrolledSubjects(new ArrayList<>());
        Subject subject = new Subject();
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(subjectRepository.findById(anyLong())).thenReturn(Optional.of(subject));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        studentService.enrollInSubject(1L, 1L);
        assertTrue(student.getEnrolledSubjects().contains(subject));
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testEnrollInSubjectStudentNotFound() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> studentService.enrollInSubject(1L, 1L));
    }

    @Test
    void testEnrollInSubjectSubjectNotFound() throws StudentNotFoundException {
        Student student = new Student();
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(subjectRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> studentService.enrollInSubject(1L, 1L));
    }

    @Test
    void testRegisterForExam() throws StudentNotFoundException {
        Student student = new Student();
        student.setRegisteredExams(new ArrayList<>());
        Exam exam = new Exam();
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(examRepository.findById(anyLong())).thenReturn(Optional.of(exam));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        studentService.registerForExam(1L, 1L);
        assertTrue(student.getRegisteredExams().contains(exam));
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testRegisterForExamStudentNotFound() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> studentService.registerForExam(1L, 1L));
    }

    @Test
    void testRegisterForExamExamNotFound() throws StudentNotFoundException {
        Student student = new Student();
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(examRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> studentService.registerForExam(1L, 1L));
    }
}