package com.example.backend.controller.academic;

import com.example.backend.models.Homework;
import com.example.backend.services.HomeworkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/homeworks")
public class HomeworkController {

    private final HomeworkService homeworkService;

    public HomeworkController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @GetMapping
    public ResponseEntity<List<Homework>> getAllHomeworks() {
        return ResponseEntity.ok(homeworkService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Homework> getHomeworkById(@PathVariable Long id) {
        return homeworkService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Homework>> getHomeworksByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(homeworkService.findByStudentId(studentId));
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<Homework>> getHomeworksByAssignmentId(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(homeworkService.findByAssignmentId(assignmentId));
    }

    @PostMapping
    public ResponseEntity<Homework> createHomework(@RequestBody Homework homework) {
        Homework savedHomework = homeworkService.save(homework);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHomework);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Homework> updateHomework(@PathVariable Long id, @RequestBody Homework homework) {
        if (!homeworkService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        homework.setId(id);
        return ResponseEntity.ok(homeworkService.save(homework));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHomework(@PathVariable Long id) {
        if (!homeworkService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        homeworkService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
