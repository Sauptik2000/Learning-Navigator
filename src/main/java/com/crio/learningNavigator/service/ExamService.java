package com.crio.learningNavigator.service;

import com.crio.learningNavigator.entity.Exam;
import com.crio.learningNavigator.exception.ExamNotFoundException;
import com.crio.learningNavigator.repository.ExamRepository;
import com.crio.learningNavigator.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService implements IExamService{
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public Exam getExamById(Long examId) throws ExamNotFoundException {
        return examRepository.findById(examId)
                .orElseThrow(() -> new ExamNotFoundException("Exam not found with id " + examId));
    }

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public Exam updateExam(Long examId, Exam exam) throws ExamNotFoundException {
        if (!examRepository.existsById(examId)) {
            throw new ExamNotFoundException("Exam not found with id " + examId);
        }
        exam.setExamId(examId);
        return examRepository.save(exam);
    }

    @Override
    public void deleteExam(Long examId) throws ExamNotFoundException {
        if (!examRepository.existsById(examId)) {
            throw new ExamNotFoundException("Exam not found with id " + examId);
        }
        examRepository.deleteById(examId);
    }
}
