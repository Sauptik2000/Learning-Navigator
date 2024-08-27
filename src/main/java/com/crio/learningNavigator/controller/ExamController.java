package com.crio.learningNavigator.controller;

import com.crio.learningNavigator.entity.Exam;
import com.crio.learningNavigator.exception.ExamNotFoundException;
import com.crio.learningNavigator.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        Exam createdExam = examService.createExam(exam);
        return ResponseEntity.ok(createdExam);
    }

    @GetMapping("/{examId}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long examId) {
        try {
            Exam exam = examService.getExamById(examId);
            return ResponseEntity.ok(exam);
        } catch (ExamNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Exam>> getAllExams() {
        List<Exam> exams = examService.getAllExams();
        return ResponseEntity.ok(exams);
    }

    @PutMapping("/{examId}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long examId, @RequestBody Exam exam) {
        try {
            Exam updatedExam = examService.updateExam(examId, exam);
            return ResponseEntity.ok(updatedExam);
        } catch (ExamNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/{examId}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long examId) {
        try {
            examService.deleteExam(examId);
            return ResponseEntity.noContent().build();
        } catch (ExamNotFoundException e) {
            return ResponseEntity.status(404).build();
        }
    }
}