package com.example.backend.controller.actors;

import com.example.backend.models.ITAdmin;
import com.example.backend.services.ITAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/it-admins")
public class ITAdminController {

    private final ITAdminService itAdminService;

    public ITAdminController(ITAdminService itAdminService) {
        this.itAdminService = itAdminService;
    }

    @GetMapping
    public ResponseEntity<List<ITAdmin>> getAllITAdmins() {
        return ResponseEntity.ok(itAdminService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ITAdmin> getITAdminById(@PathVariable Long id) {
        return itAdminService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ITAdmin> getITAdminByUserId(@PathVariable Long userId) {
        return itAdminService.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<ITAdmin>> getITAdminsBySchoolId(@PathVariable Long schoolId) {
        return ResponseEntity.ok(itAdminService.findBySchoolId(schoolId));
    }

    @PostMapping
    public ResponseEntity<ITAdmin> createITAdmin(@RequestBody ITAdmin itAdmin) {
        ITAdmin savedITAdmin = itAdminService.save(itAdmin);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedITAdmin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ITAdmin> updateITAdmin(@PathVariable Long id, @RequestBody ITAdmin itAdmin) {
        if (!itAdminService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        itAdmin.setId(id);
        return ResponseEntity.ok(itAdminService.save(itAdmin));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteITAdmin(@PathVariable Long id) {
        if (!itAdminService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        itAdminService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
