package com.example.backend.controller.actors;

import com.example.backend.models.Parent;
import com.example.backend.services.ParentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
public class ParentController {

    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @GetMapping
    public ResponseEntity<List<Parent>> getAllParents() {
        return ResponseEntity.ok(parentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parent> getParentById(@PathVariable Long id) {
        return parentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Parent> getParentByUserId(@PathVariable Long userId) {
        return parentService.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Parent> createParent(@RequestBody Parent parent) {
        Parent savedParent = parentService.save(parent);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedParent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parent> updateParent(@PathVariable Long id, @RequestBody Parent parent) {
        if (!parentService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        parent.setId(id);
        return ResponseEntity.ok(parentService.save(parent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParent(@PathVariable Long id) {
        if (!parentService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        parentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
