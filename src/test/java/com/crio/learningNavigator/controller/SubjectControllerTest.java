package com.crio.learningNavigator.controller;

import com.crio.learningNavigator.entity.Subject;
import com.crio.learningNavigator.exception.SubjectNotFoundException;
import com.crio.learningNavigator.service.SubjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

class SubjectControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SubjectService subjectService;

    @InjectMocks
    private SubjectController subjectController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(subjectController).build();
    }

    @Test
    void testCreateSubject() throws Exception {
        Subject subject = new Subject(1L, "Mathematics", null, null);
        when(subjectService.createSubject(any(Subject.class))).thenReturn(subject);

        mockMvc.perform(post("/subjects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(subject)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subjectId").value(1))
                .andExpect(jsonPath("$.subjectName").value("Mathematics"));
    }

    @Test
    void testGetSubjectById() throws Exception {
        Subject subject = new Subject(1L, "Mathematics", null, null);
        when(subjectService.getSubjectById(1L)).thenReturn(subject);

        mockMvc.perform(get("/subjects/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subjectId").value(1))
                .andExpect(jsonPath("$.subjectName").value("Mathematics"));
    }

    @Test
    void testGetSubjectByIdNotFound() throws Exception {
        when(subjectService.getSubjectById(anyLong())).thenThrow(SubjectNotFoundException.class);

        mockMvc.perform(get("/subjects/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllSubjects() throws Exception {
        List<Subject> subjects = Arrays.asList(
                new Subject(1L, "Mathematics", null, null),
                new Subject(2L, "Science", null, null)
        );
        when(subjectService.getAllSubjects()).thenReturn(subjects);

        mockMvc.perform(get("/subjects")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].subjectId").value(1))
                .andExpect(jsonPath("$[0].subjectName").value("Mathematics"))
                .andExpect(jsonPath("$[1].subjectId").value(2))
                .andExpect(jsonPath("$[1].subjectName").value("Science"));
    }

    @Test
    void testUpdateSubject() throws Exception {
        Subject updatedSubject = new Subject(1L, "Updated Mathematics", null, null);
        when(subjectService.updateSubject(anyLong(), any(Subject.class))).thenReturn(updatedSubject);

        mockMvc.perform(put("/subjects/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedSubject)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subjectId").value(1))
                .andExpect(jsonPath("$.subjectName").value("Updated Mathematics"));
    }

    @Test
    void testUpdateSubjectNotFound() throws Exception {
        when(subjectService.updateSubject(anyLong(), any(Subject.class))).thenThrow(SubjectNotFoundException.class);

        mockMvc.perform(put("/subjects/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(new Subject())))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteSubject() throws Exception {
        mockMvc.perform(delete("/subjects/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(subjectService, times(1)).deleteSubject(1L);
    }

    @Test
    void testDeleteSubjectNotFound() throws Exception {
        doThrow(SubjectNotFoundException.class).when(subjectService).deleteSubject(anyLong());

        mockMvc.perform(delete("/subjects/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
