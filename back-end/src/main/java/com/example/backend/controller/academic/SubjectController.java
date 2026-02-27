package com.example.backend.controller.academic;

import com.example.backend.models.Subject;
import com.example.backend.services.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long id) {
        return subjectService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<List<Subject>> getSubjectsByClassroomId(@PathVariable Long classroomId) {
        return ResponseEntity.ok(subjectService.findByClassroomId(classroomId));
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<Subject> getSubjectByTeacherId(@PathVariable Long teacherId) {
        return subjectService.findByTeacherId(teacherId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        Subject savedSubject = subjectService.save(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSubject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Long id, @RequestBody Subject subject) {
        if (!subjectService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        subject.setId(id);
        return ResponseEntity.ok(subjectService.save(subject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        if (!subjectService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        subjectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
