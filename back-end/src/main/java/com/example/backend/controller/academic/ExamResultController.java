package com.example.backend.controller.academic;

import com.example.backend.models.ExamResult;
import com.example.backend.services.ExamResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam-results")
public class ExamResultController {

    private final ExamResultService examResultService;

    public ExamResultController(ExamResultService examResultService) {
        this.examResultService = examResultService;
    }

    @GetMapping
    public ResponseEntity<List<ExamResult>> getAllExamResults() {
        return ResponseEntity.ok(examResultService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamResult> getExamResultById(@PathVariable Long id) {
        return examResultService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ExamResult>> getExamResultsByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(examResultService.findByStudentId(studentId));
    }

    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<ExamResult>> getExamResultsByExamId(@PathVariable Long examId) {
        return ResponseEntity.ok(examResultService.findByExamId(examId));
    }

    @PostMapping
    public ResponseEntity<ExamResult> createExamResult(@RequestBody ExamResult examResult) {
        ExamResult savedExamResult = examResultService.save(examResult);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExamResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamResult> updateExamResult(@PathVariable Long id, @RequestBody ExamResult examResult) {
        if (!examResultService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        examResult.setId(id);
        return ResponseEntity.ok(examResultService.save(examResult));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExamResult(@PathVariable Long id) {
        if (!examResultService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        examResultService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
