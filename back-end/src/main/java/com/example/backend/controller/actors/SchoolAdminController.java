package com.example.backend.controller.actors;

import com.example.backend.models.SchoolAdmin;
import com.example.backend.services.SchoolAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/school-admins")
public class SchoolAdminController {

    private final SchoolAdminService schoolAdminService;

    public SchoolAdminController(SchoolAdminService schoolAdminService) {
        this.schoolAdminService = schoolAdminService;
    }

    @GetMapping
    public ResponseEntity<List<SchoolAdmin>> getAllSchoolAdmins() {
        return ResponseEntity.ok(schoolAdminService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolAdmin> getSchoolAdminById(@PathVariable Long id) {
        return schoolAdminService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<SchoolAdmin> getSchoolAdminByUserId(@PathVariable Long userId) {
        return schoolAdminService.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<SchoolAdmin>> getSchoolAdminsBySchoolId(@PathVariable Long schoolId) {
        return ResponseEntity.ok(schoolAdminService.findBySchoolId(schoolId));
    }

    @PostMapping
    public ResponseEntity<SchoolAdmin> createSchoolAdmin(@RequestBody SchoolAdmin schoolAdmin) {
        SchoolAdmin savedSchoolAdmin = schoolAdminService.save(schoolAdmin);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSchoolAdmin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchoolAdmin> updateSchoolAdmin(@PathVariable Long id, @RequestBody SchoolAdmin schoolAdmin) {
        if (!schoolAdminService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        schoolAdmin.setId(id);
        return ResponseEntity.ok(schoolAdminService.save(schoolAdmin));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchoolAdmin(@PathVariable Long id) {
        if (!schoolAdminService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        schoolAdminService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
