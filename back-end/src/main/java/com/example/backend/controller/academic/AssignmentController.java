package com.example.backend.controller.academic;

import com.example.backend.dto.assignment.AssignmentDTO;
import com.example.backend.dto.assignment.AssignmentUploadDTO;
import com.example.backend.models.Assignment;
import com.example.backend.services.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }


    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping()
    public ResponseEntity<Map<String, String>> uploadAssignment( @ModelAttribute AssignmentUploadDTO asignmentUploadDTO) {
        try {
            assignmentService.uploadAssignmentFile(asignmentUploadDTO.getFile(), asignmentUploadDTO.getSubjectId(), asignmentUploadDTO.getDeadline(), asignmentUploadDTO.getDescription());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Failed to upload assignment"));
        }
        return ResponseEntity.ok().body(Map.of("message", "Assignment uploaded successfully"));
    }
    






    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<AssignmentDTO>> getAssignmentsBySubjectId(@PathVariable Long subjectId) {
        return ResponseEntity.ok(assignmentService.findAssignmentBySubjectId(subjectId)) ;
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
