package com.example.backend.controller.academic;

import com.example.backend.models.Exam;
import com.example.backend.services.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping
    public ResponseEntity<List<Exam>> getAllExams() {
        return ResponseEntity.ok(examService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long id) {
        return examService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<List<Exam>> getExamsByClassroomId(@PathVariable Long classroomId) {
        return ResponseEntity.ok(examService.findByClassroomId(classroomId));
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Exam>> getExamsByTeacherId(@PathVariable Long teacherId) {
        return ResponseEntity.ok(examService.findByTeacherId(teacherId));
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<Exam>> getExamsBySubjectId(@PathVariable Long subjectId) {
        return ResponseEntity.ok(examService.findBySubjectId(subjectId));
    }

    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        Exam savedExam = examService.save(exam);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExam);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long id, @RequestBody Exam exam) {
        if (!examService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        exam.setId(id);
        return ResponseEntity.ok(examService.save(exam));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        if (!examService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        examService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
