package com.crio.learningNavigator.controller;

import com.crio.learningNavigator.entity.*;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.crio.learningNavigator.entity.Exam;
import com.crio.learningNavigator.exception.ExamNotFoundException;
import com.crio.learningNavigator.service.ExamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;



public class ExamControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ExamService examService;

    @InjectMocks
    private ExamController examController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(examController).build();
    }

    @Test
    void testCreateExam() throws Exception {
        Subject subject = new Subject(); // Assuming Subject class exists and has a default constructor
        Exam exam = new Exam(1L, subject, null);
        when(examService.createExam(any(Exam.class))).thenReturn(exam);

        mockMvc.perform(post("/exams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(exam)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.examId").value(1))
                .andExpect(jsonPath("$.subject").exists())
                .andExpect(jsonPath("$.enrolledStudents").doesNotExist());
    }

    @Test
    void testGetExamById() throws Exception {
        Subject subject = new Subject(); // Assuming Subject class exists and has a default constructor
        Exam exam = new Exam(1L, subject, null);
        when(examService.getExamById(1L)).thenReturn(exam);

        mockMvc.perform(get("/exams/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.examId").value(1))
                .andExpect(jsonPath("$.subject").exists())
                .andExpect(jsonPath("$.enrolledStudents").doesNotExist());
    }

    @Test
    void testGetExamByIdNotFound() throws Exception {
        when(examService.getExamById(anyLong())).thenThrow(ExamNotFoundException.class);

        mockMvc.perform(get("/exams/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllExams() throws Exception {
        Subject subject = new Subject(); // Assuming Subject class exists and has a default constructor
        List<Exam> exams = Arrays.asList(
                new Exam(1L, subject, null),
                new Exam(2L, subject, null)
        );
        when(examService.getAllExams()).thenReturn(exams);

        mockMvc.perform(get("/exams")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].examId").value(1))
                .andExpect(jsonPath("$[1].examId").value(2));
    }

    @Test
    void testUpdateExam() throws Exception {
        Subject subject = new Subject(); // Assuming Subject class exists and has a default constructor
        Exam updatedExam = new Exam(1L, subject, null);
        when(examService.updateExam(anyLong(), any(Exam.class))).thenReturn(updatedExam);

        mockMvc.perform(put("/exams/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedExam)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.examId").value(1))
                .andExpect(jsonPath("$.subject").exists())
                .andExpect(jsonPath("$.enrolledStudents").doesNotExist());
    }

    @Test
    void testUpdateExamNotFound() throws Exception {
        when(examService.updateExam(anyLong(), any(Exam.class))).thenThrow(ExamNotFoundException.class);

        mockMvc.perform(put("/exams/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(new Exam())))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteExam() throws Exception {
        mockMvc.perform(delete("/exams/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(examService, times(1)).deleteExam(1L);
    }

    @Test
    void testDeleteExamNotFound() throws Exception {
        doThrow(ExamNotFoundException.class).when(examService).deleteExam(anyLong());

        mockMvc.perform(delete("/exams/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
