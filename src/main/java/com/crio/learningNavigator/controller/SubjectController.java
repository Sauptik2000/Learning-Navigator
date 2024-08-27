package com.crio.learningNavigator.controller;

import com.crio.learningNavigator.entity.Subject;
import com.crio.learningNavigator.exception.SubjectNotFoundException;
import com.crio.learningNavigator.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        Subject createdSubject = subjectService.createSubject(subject);
        return ResponseEntity.ok(createdSubject);
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long subjectId) {
        try {
            Subject subject = subjectService.getSubjectById(subjectId);
            return ResponseEntity.ok(subject);
        } catch (SubjectNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @PutMapping("/{subjectId}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Long subjectId, @RequestBody Subject subject) {
        try {
            Subject updatedSubject = subjectService.updateSubject(subjectId, subject);
            return ResponseEntity.ok(updatedSubject);
        } catch (SubjectNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long subjectId) {
        try {
            subjectService.deleteSubject(subjectId);
            return ResponseEntity.noContent().build();
        } catch (SubjectNotFoundException e) {
            return ResponseEntity.status(404).build();
        }
    }
}