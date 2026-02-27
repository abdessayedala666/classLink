package com.example.backend.controller.academic;

import com.example.backend.models.Assignment;
import com.example.backend.services.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping
    public ResponseEntity<List<Assignment>> getAllAssignments() {
        return ResponseEntity.ok(assignmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable Long id) {
        return assignmentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<List<Assignment>> getAssignmentsByClassroomId(@PathVariable Long classroomId) {
        return ResponseEntity.ok(assignmentService.findByClassroomId(classroomId));
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Assignment>> getAssignmentsByTeacherId(@PathVariable Long teacherId) {
        return ResponseEntity.ok(assignmentService.findByTeacherId(teacherId));
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<Assignment>> getAssignmentsBySubjectId(@PathVariable Long subjectId) {
        return ResponseEntity.ok(assignmentService.findBySubjectId(subjectId));
    }

    @PostMapping
    public ResponseEntity<Assignment> createAssignment(@RequestBody Assignment assignment) {
        Assignment savedAssignment = assignmentService.save(assignment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAssignment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Assignment> updateAssignment(@PathVariable Long id, @RequestBody Assignment assignment) {
        if (!assignmentService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        assignment.setId(id);
        return ResponseEntity.ok(assignmentService.save(assignment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        if (!assignmentService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        assignmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
