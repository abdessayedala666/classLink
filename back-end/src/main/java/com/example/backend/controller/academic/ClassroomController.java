package com.example.backend.controller.academic;

import com.example.backend.dto.classroom.ClassroomDTO;
import com.example.backend.dto.classroom.CreateClassroomRequest;
import com.example.backend.models.Classroom;
import com.example.backend.services.ClassroomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/grades")
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }



    @GetMapping("/{id}")
    public ResponseEntity<Classroom> getClassroomById(@PathVariable Long id) {
        return classroomService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("{gradeId}/classrooms")
    public ResponseEntity<List<ClassroomDTO>> getClassroomsByGradeId(@PathVariable Long gradeId) {
        return ResponseEntity.ok(classroomService.getClassroomsByGradeId(gradeId));
    }

    @PostMapping("/{gradeId}/classrooms")
    public ResponseEntity<Map<String, String>> createClassroom(@PathVariable Long gradeId, @RequestBody CreateClassroomRequest request) {
        request.setGradeId(gradeId);
        classroomService.createClassroom(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Classroom created successfully"));
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
