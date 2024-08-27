package com.crio.learningNavigator.service;

import com.crio.learningNavigator.entity.Subject;
import com.crio.learningNavigator.exception.SubjectNotFoundException;
import com.crio.learningNavigator.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService implements ISubjectService{
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject getSubjectById(Long subjectId) throws SubjectNotFoundException {
        return subjectRepository.findById(subjectId)
                .orElseThrow(() -> new SubjectNotFoundException("Subject not found with id " + subjectId));
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject updateSubject(Long subjectId, Subject subject) throws SubjectNotFoundException {
        if (!subjectRepository.existsById(subjectId)) {
            throw new SubjectNotFoundException("Subject not found with id " + subjectId);
        }
        subject.setSubjectId(subjectId);
        return subjectRepository.save(subject);
    }

    @Override
    public void deleteSubject(Long subjectId) throws SubjectNotFoundException {
        if (!subjectRepository.existsById(subjectId)) {
            throw new SubjectNotFoundException("Subject not found with id " + subjectId);
        }
        subjectRepository.deleteById(subjectId);
    }
}
