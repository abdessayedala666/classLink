package com.example.backend.controller.academic;

import com.example.backend.models.Classroom;
import com.example.backend.services.ClassroomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    public ResponseEntity<List<Classroom>> getAllClassrooms() {
        return ResponseEntity.ok(classroomService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classroom> getClassroomById(@PathVariable Long id) {
        return classroomService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/grade/{gradeId}")
    public ResponseEntity<List<Classroom>> getClassroomsByGradeId(@PathVariable Long gradeId) {
        return ResponseEntity.ok(classroomService.findByGradeId(gradeId));
    }

    @PostMapping
    public ResponseEntity<Classroom> createClassroom(@RequestBody Classroom classroom) {
        Classroom savedClassroom = classroomService.save(classroom);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClassroom);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classroom> updateClassroom(@PathVariable Long id, @RequestBody Classroom classroom) {
        if (!classroomService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        classroom.setId(id);
        return ResponseEntity.ok(classroomService.save(classroom));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable Long id) {
        if (!classroomService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        classroomService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
