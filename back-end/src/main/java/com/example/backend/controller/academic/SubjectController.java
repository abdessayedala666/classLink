package com.example.backend.controller.academic;

import com.example.backend.models.Subject;
import com.example.backend.services.SubjectService;
import com.example.backend.dto.subject.CreateSubjectDTO;
import com.example.backend.dto.subject.SubjectDTO;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;




@RestController
@RequestMapping("/api/classrooms")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/{classroomId}/subjects")
    public ResponseEntity<Map<String , String>> createSubject(@PathVariable Long classroomId , @Valid @RequestBody CreateSubjectDTO request ) {
        request.setClassroomId(classroomId);
        subjectService.createSubject(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Subject created successfully"));    
    }
    @GetMapping("/{classroomId}/subjects")
    public ResponseEntity<List<SubjectDTO>> getSubjectsByClassroomId(@PathVariable Long classroomId) {
        return ResponseEntity.ok(subjectService.getSubjectsByClassroomId(classroomId));

    }
    
    @PostMapping("/subjects/{subjectId}/teachers/{teacherId}")
    public ResponseEntity<Map<String , String>> assignTeacherToSubject(@PathVariable Long teacherId  , 
                                                                        @PathVariable Long subjectId
                                                                        ) {
        subjectService.assignTeacherToSubject(teacherId , subjectId) ; 
        return ResponseEntity.ok(Map.of("message", "Teacher assigned to subject successfully"));                                                                  

        
    }
    
    
}
