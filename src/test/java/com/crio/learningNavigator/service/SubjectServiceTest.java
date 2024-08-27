package com.crio.learningNavigator.service;

import com.crio.learningNavigator.entity.Subject;
import com.crio.learningNavigator.exception.SubjectNotFoundException;
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

class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private SubjectService subjectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateSubject() {
        Subject subject = new Subject();
        when(subjectRepository.save(any(Subject.class))).thenReturn(subject);

        Subject createdSubject = subjectService.createSubject(subject);
        assertNotNull(createdSubject);
        verify(subjectRepository, times(1)).save(subject);
    }

    @Test
    void testGetSubjectById() throws SubjectNotFoundException {
        Subject subject = new Subject();
        when(subjectRepository.findById(anyLong())).thenReturn(Optional.of(subject));

        Subject retrievedSubject = subjectService.getSubjectById(1L);
        assertNotNull(retrievedSubject);
        verify(subjectRepository, times(1)).findById(1L);
    }

    @Test
    void testGetSubjectByIdNotFound() {
        when(subjectRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(SubjectNotFoundException.class, () -> subjectService.getSubjectById(1L));
    }

    @Test
    void testGetAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject());
        when(subjectRepository.findAll()).thenReturn(subjects);

        List<Subject> retrievedSubjects = subjectService.getAllSubjects();
        assertNotNull(retrievedSubjects);
        assertEquals(1, retrievedSubjects.size());
        verify(subjectRepository, times(1)).findAll();
    }

    @Test
    void testUpdateSubject() throws SubjectNotFoundException {
        Subject existingSubject = new Subject();
        Subject updatedSubject = new Subject();
        when(subjectRepository.existsById(anyLong())).thenReturn(true);
        when(subjectRepository.save(any(Subject.class))).thenReturn(updatedSubject);

        Subject result = subjectService.updateSubject(1L, updatedSubject);
        assertNotNull(result);
        assertEquals(1L, result.getSubjectId());
        verify(subjectRepository, times(1)).save(updatedSubject);
    }

    @Test
    void testUpdateSubjectNotFound() {
        when(subjectRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(SubjectNotFoundException.class, () -> subjectService.updateSubject(1L, new Subject()));
    }

    @Test
    void testDeleteSubject() throws SubjectNotFoundException {
        when(subjectRepository.existsById(anyLong())).thenReturn(true);

        subjectService.deleteSubject(1L);
        verify(subjectRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteSubjectNotFound() {
        when(subjectRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(SubjectNotFoundException.class, () -> subjectService.deleteSubject(1L));
    }
}