package com.crio.learningNavigator.service;

import com.crio.learningNavigator.entity.Exam;
import com.crio.learningNavigator.exception.ExamNotFoundException;

import java.util.List;

public interface IExamService {
    Exam createExam(Exam exam);
    Exam getExamById(Long examId) throws ExamNotFoundException;
    List<Exam> getAllExams();
    Exam updateExam(Long examId, Exam exam) throws ExamNotFoundException;
    void deleteExam(Long examId) throws ExamNotFoundException;
}