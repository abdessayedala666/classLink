package com.example.backend.controller.academic;

import com.example.backend.dto.grade.CreateGradeRequest;
import com.example.backend.dto.grade.GradeDTO;
import com.example.backend.models.Grade;
import com.example.backend.services.GradeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/school/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }


    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    @PostMapping
    public ResponseEntity<Map<String , String>> createGrade(@Valid @RequestBody CreateGradeRequest request) {
        gradeService.createGrade(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Grade created successfully"));
    }
    
    

    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    @GetMapping("")
    public ResponseEntity<List<GradeDTO>> getAllGrades() {

        return ResponseEntity.ok(gradeService.getGrades());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grade> getGradeById(@PathVariable Long id) {
        return gradeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<Grade>> getGradesBySchoolId(@PathVariable Long schoolId) {
        return ResponseEntity.ok(gradeService.findBySchoolId(schoolId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @RequestBody Grade grade) {
        if (!gradeService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        grade.setId(id);
        return ResponseEntity.ok(gradeService.save(grade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        if (!gradeService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        gradeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
