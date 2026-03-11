package com.example.backend.controller.core;

import com.example.backend.dto.school.SchoolDTO;
import com.example.backend.models.School;
import com.example.backend.services.SchoolService;
import com.example.backend.services.SpiceDBAuthorizationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    private final SchoolService schoolService;
   

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("")
    public ResponseEntity<SchoolDTO> getSchoolByAdmin() {
        return ResponseEntity.ok(schoolService.getSchoolByAdmin());
    }
    


    @GetMapping("/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable Long id) {
        return schoolService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<School> getSchoolByName(@PathVariable String name) {
        return schoolService.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<School> createSchool(@RequestBody School school) {
        School savedSchool = schoolService.save(school);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSchool);
    }

    @PutMapping("/{id}")
    public ResponseEntity<School> updateSchool(@PathVariable Long id, @RequestBody School school) {
        if (!schoolService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        school.setId(id);
        return ResponseEntity.ok(schoolService.save(school));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
        if (!schoolService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        schoolService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
