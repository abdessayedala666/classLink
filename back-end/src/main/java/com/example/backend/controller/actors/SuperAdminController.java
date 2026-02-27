package com.example.backend.controller.actors;

import com.example.backend.models.SuperAdmin;
import com.example.backend.services.SuperAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/super-admins")
public class SuperAdminController {

    private final SuperAdminService superAdminService;

    public SuperAdminController(SuperAdminService superAdminService) {
        this.superAdminService = superAdminService;
    }

    @GetMapping
    public ResponseEntity<List<SuperAdmin>> getAllSuperAdmins() {
        return ResponseEntity.ok(superAdminService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuperAdmin> getSuperAdminById(@PathVariable Long id) {
        return superAdminService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<SuperAdmin> getSuperAdminByUserId(@PathVariable Long userId) {
        return superAdminService.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SuperAdmin> createSuperAdmin(@RequestBody SuperAdmin superAdmin) {
        SuperAdmin savedSuperAdmin = superAdminService.save(superAdmin);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSuperAdmin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuperAdmin> updateSuperAdmin(@PathVariable Long id, @RequestBody SuperAdmin superAdmin) {
        if (!superAdminService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        superAdmin.setId(id);
        return ResponseEntity.ok(superAdminService.save(superAdmin));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSuperAdmin(@PathVariable Long id) {
        if (!superAdminService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        superAdminService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
