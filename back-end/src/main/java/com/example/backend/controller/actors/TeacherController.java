package com.example.backend.controller.actors;

import com.example.backend.models.Teacher;
import com.example.backend.services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PreAuthorize("hasRole('SCHOOL_ADMIN')")
    @PostMapping("/{teacherId}/subject/{subjectId}")
    public ResponseEntity<Map<String , String>> assignSubjectToTeacher(@PathVariable Long teacherId, @PathVariable Long subjectId) {

        teacherService.assignSubjectToTeacher(teacherId, subjectId);

        return ResponseEntity.ok(Map.of("message", "Subject assigned to teacher successfully"));
    }


    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        return teacherService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Teacher> getTeacherByUserId(@PathVariable Long userId) {
        return teacherService.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<Teacher>> getTeachersBySchoolId(@PathVariable Long schoolId) {
        return ResponseEntity.ok(teacherService.findBySchoolId(schoolId));
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        Teacher savedTeacher = teacherService.save(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        if (!teacherService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        teacher.setId(id);
        return ResponseEntity.ok(teacherService.save(teacher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        if (!teacherService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        teacherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
