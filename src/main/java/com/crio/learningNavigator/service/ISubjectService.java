package com.crio.learningNavigator.service;

import com.crio.learningNavigator.entity.Subject;
import com.crio.learningNavigator.exception.SubjectNotFoundException;

import java.util.List;

public interface ISubjectService {
    Subject createSubject(Subject subject);
    Subject getSubjectById(Long subjectId) throws SubjectNotFoundException;
    List<Subject> getAllSubjects();
    Subject updateSubject(Long subjectId, Subject subject) throws SubjectNotFoundException;
    void deleteSubject(Long subjectId) throws SubjectNotFoundException;
}