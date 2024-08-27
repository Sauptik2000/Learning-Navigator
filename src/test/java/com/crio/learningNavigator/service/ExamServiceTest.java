package com.crio.learningNavigator.service;

import com.crio.learningNavigator.entity.Exam;
import com.crio.learningNavigator.entity.Subject;
import com.crio.learningNavigator.exception.ExamNotFoundException;
import com.crio.learningNavigator.repository.ExamRepository;
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

class ExamServiceTest {

    @Mock
    private ExamRepository examRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private ExamService examService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateExam() {
        Exam exam = new Exam();
        when(examRepository.save(any(Exam.class))).thenReturn(exam);

        Exam createdExam = examService.createExam(exam);
        assertNotNull(createdExam);
        verify(examRepository, times(1)).save(exam);
    }

    @Test
    void testGetExamById() throws ExamNotFoundException {
        Exam exam = new Exam();
        when(examRepository.findById(anyLong())).thenReturn(Optional.of(exam));

        Exam retrievedExam = examService.getExamById(1L);
        assertNotNull(retrievedExam);
        verify(examRepository, times(1)).findById(1L);
    }

    @Test
    void testGetExamByIdNotFound() {
        when(examRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ExamNotFoundException.class, () -> examService.getExamById(1L));
    }

    @Test
    void testGetAllExams() {
        List<Exam> exams = new ArrayList<>();
        exams.add(new Exam());
        when(examRepository.findAll()).thenReturn(exams);

        List<Exam> retrievedExams = examService.getAllExams();
        assertNotNull(retrievedExams);
        assertEquals(1, retrievedExams.size());
        verify(examRepository, times(1)).findAll();
    }

    @Test
    void testUpdateExam() throws ExamNotFoundException {
        Exam existingExam = new Exam();
        Exam updatedExam = new Exam();
        when(examRepository.existsById(anyLong())).thenReturn(true);
        when(examRepository.save(any(Exam.class))).thenReturn(updatedExam);

        Exam result = examService.updateExam(1L, updatedExam);
        assertNotNull(result);
        assertEquals(1L, result.getExamId());
        verify(examRepository, times(1)).save(updatedExam);
    }

    @Test
    void testUpdateExamNotFound() {
        when(examRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(ExamNotFoundException.class, () -> examService.updateExam(1L, new Exam()));
    }

    @Test
    void testDeleteExam() throws ExamNotFoundException {
        when(examRepository.existsById(anyLong())).thenReturn(true);

        examService.deleteExam(1L);
        verify(examRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteExamNotFound() {
        when(examRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(ExamNotFoundException.class, () -> examService.deleteExam(1L));
    }
}