package com.crio.learningNavigator.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

import com.crio.learningNavigator.entity.Student;
import com.crio.learningNavigator.exception.StudentNotFoundException;
import com.crio.learningNavigator.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

class StudentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    void testCreateStudent() throws Exception {
        Student student = new Student(1L, "John Doe", null, null);
        when(studentService.createStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentName").value("John Doe"));
    }

    @Test
    void testGetStudentById() throws Exception {
        Student student = new Student(1L, "John Doe", null, null);
        when(studentService.getStudentById(1L)).thenReturn(student);

        mockMvc.perform(get("/students/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentName").value("John Doe"));
    }

    @Test
    void testGetStudentByIdNotFound() throws Exception {
        when(studentService.getStudentById(anyLong())).thenThrow(StudentNotFoundException.class);

        mockMvc.perform(get("/students/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateStudent() throws Exception {
        Student updatedStudent = new Student(1L, "Jane Doe", null, null);
        when(studentService.updateStudent(anyLong(), any(Student.class))).thenReturn(updatedStudent);

        mockMvc.perform(put("/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedStudent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentName").value("Jane Doe"));
    }

    @Test
    void testUpdateStudentNotFound() throws Exception {
        when(studentService.updateStudent(anyLong(), any(Student.class))).thenThrow(StudentNotFoundException.class);

        mockMvc.perform(put("/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new Student())))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteStudent() throws Exception {
        mockMvc.perform(delete("/students/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(studentService, times(1)).deleteStudent(1L);
    }

    @Test
    void testDeleteStudentNotFound() throws Exception {
        doThrow(StudentNotFoundException.class).when(studentService).deleteStudent(anyLong());

        mockMvc.perform(delete("/students/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllStudents() throws Exception {
        List<Student> students = Arrays.asList(
                new Student(1L, "John Doe", null, null),
                new Student(2L, "Jane Doe", null, null)
        );
        when(studentService.getAllStudents()).thenReturn(students);

        mockMvc.perform(get("/students")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studentName").value("John Doe"))
                .andExpect(jsonPath("$[1].studentName").value("Jane Doe"));
    }

    @Test
    void testEnrollInSubject() throws Exception {
        mockMvc.perform(post("/students/1/subjects/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(studentService, times(1)).enrollInSubject(1L, 2L);
    }

    @Test
    void testEnrollInSubjectNotFound() throws Exception {
        doThrow(StudentNotFoundException.class).when(studentService).enrollInSubject(anyLong(), anyLong());

        mockMvc.perform(post("/students/1/subjects/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testRegisterForExam() throws Exception {
        mockMvc.perform(post("/students/1/exams/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(studentService, times(1)).registerForExam(1L, 2L);
    }

    @Test
    void testRegisterForExamNotFound() throws Exception {
        doThrow(StudentNotFoundException.class).when(studentService).registerForExam(anyLong(), anyLong());

        mockMvc.perform(post("/students/1/exams/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
