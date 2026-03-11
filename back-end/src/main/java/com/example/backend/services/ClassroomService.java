package com.example.backend.services;

import com.example.backend.dto.classroom.ClassroomDTO;
import com.example.backend.dto.classroom.CreateClassroomRequest;
import com.example.backend.exceptions.SpiceDBException;
import com.example.backend.models.Classroom;
import com.example.backend.models.Grade;
import com.example.backend.models.School;
import com.example.backend.models.SchoolAdmin;
import com.example.backend.repository.ClassroomRepository;
import com.example.backend.repository.GradeRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final AuthService authService ; 
    private final SpiceDBAuthorizationService spiceDBAuthorizationService ;
    private final GradeRepository gradeRepository ;

    
    public ClassroomService(ClassroomRepository classroomRepository, AuthService authService, SpiceDBAuthorizationService spiceDBAuthorizationService, GradeRepository gradeRepository) {
        this.classroomRepository = classroomRepository;
        this.authService = authService;
        this.spiceDBAuthorizationService = spiceDBAuthorizationService;
        this.gradeRepository = gradeRepository;
    }

    @Transactional
    public void createClassroom (CreateClassroomRequest request){
        Long gradeId = request.getGradeId();
        if (gradeId == null) {
            throw new IllegalArgumentException("gradeId is required");
        }
        SchoolAdmin schoolAdmin = authService.findCurrentSchoolAdmin() ;
        boolean SpiceDBResult = spiceDBAuthorizationService.checkPermission(
            "schooladmin" ,
            schoolAdmin.getId().toString() ,
            "manage" ,
            "grade" ,
            gradeId.toString()
        ) ;
        if(!SpiceDBResult){
            throw new SecurityException("You do not have permission to manage classrooms for this grade");
        }
        boolean existsingClassroom = classroomRepository.existsByNameAndGradeId(request.getClassroomName().trim(), gradeId) ;
        if (existsingClassroom){
            throw new IllegalArgumentException("Classroom with the same name already exists in this grade");
        }
        Grade grade = gradeRepository.findById(gradeId)
            .orElseThrow(() -> new IllegalArgumentException("Grade not found with id: " + gradeId)) ;
        Classroom classroom = new Classroom() ;
        classroom.setName(request.getClassroomName().trim());
        classroom.setGrade(grade);
        Classroom saved = classroomRepository.save(classroom) ;
        try {
            spiceDBAuthorizationService.makeRelationship(
                "grade" ,
                gradeId.toString() ,
                "grade_owner" ,
                "classroom" ,
                saved.getId().toString()
            ) ;
        } catch (Exception e) {
            throw new SpiceDBException("Failed to create relationship in SpiceDB: " + e.getMessage());
        }
    }
    public List<ClassroomDTO> getClassroomsByGradeId(Long gradeId) {
        SchoolAdmin schoolAdmin = authService.findCurrentSchoolAdmin() ;
        boolean SpiceDBResult = spiceDBAuthorizationService.checkPermission(
            "schooladmin" ,
            schoolAdmin.getId().toString() ,
            "manage" ,
            "grade" ,
            gradeId.toString()
        ) ;
        if(!SpiceDBResult){
            throw new SecurityException("You do not have permission to view classrooms for this grade");
        }
        List<Classroom> classrooms = classroomRepository.findByGradeId(gradeId) ;
        return classrooms.stream().map(ClassroomDTO::new)
            .toList() ; 
    }

    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    public Optional<Classroom> findById(Long id) {
        return classroomRepository.findById(id);
    }

    public List<Classroom> findByGradeId(Long gradeId) {
        return classroomRepository.findByGradeId(gradeId);
    }

    public Classroom save(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    public void deleteById(Long id) {
        classroomRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return classroomRepository.existsById(id);
    }
}
