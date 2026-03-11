package com.example.backend.services;

import com.example.backend.exceptions.SpiceDBException;
import com.example.backend.dto.grade.CreateGradeRequest;
import com.example.backend.dto.grade.GradeDTO;
import com.example.backend.models.Grade;
import com.example.backend.models.School;
import com.example.backend.models.SchoolAdmin;
import com.example.backend.repository.GradeRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;
    private final AuthService authService ;
    private final SpiceDBAuthorizationService spiceDBAuthorizationService ;

    public GradeService(GradeRepository gradeRepository,
                        AuthService authService,
                        SpiceDBAuthorizationService spiceDBAuthorizationService
    ) {
        this.gradeRepository = gradeRepository;
        this.authService = authService ;
        this.spiceDBAuthorizationService = spiceDBAuthorizationService ;
    }

    @Transactional
    public void createGrade(CreateGradeRequest request) {
        SchoolAdmin schoolAdmin = authService.findCurrentSchoolAdmin() ;
        School school = schoolAdmin.getSchool() ;
        boolean existingGrade = gradeRepository.existsByNameAndSchoolId(request.getName().trim(), school.getId()) ;
        if (existingGrade){
            throw new IllegalArgumentException("Grade with the same name already exists in this school");
        }
        Grade grade = new Grade() ;
        grade.setName(request.getName().trim());
        grade.setSchool(school);
        Grade saved = gradeRepository.save(grade);
        try {
            spiceDBAuthorizationService.makeRelationship(
                "school" ,
                school.getId().toString() ,
                "school_owner" ,
                "grade" ,
                saved.getId().toString()
            ) ;
        } catch (Exception e) {
            throw new SpiceDBException("Failed to create relationship in SpiceDB: " + e.getMessage());
        }
    }
    public List<GradeDTO> getGrades() {
        SchoolAdmin schoolAdmin = authService.findCurrentSchoolAdmin() ;
        School school = schoolAdmin.getSchool() ;
        List<Grade> grades = gradeRepository.findBySchoolId(school.getId()) ;
        return grades.stream().map(GradeDTO::new)
            .toList() ; 
    }
    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

    public Optional<Grade> findById(Long id) {
        return gradeRepository.findById(id);
    }

    public List<Grade> findBySchoolId(Long schoolId) {
        return gradeRepository.findBySchoolId(schoolId);
    }

    public Grade save(Grade grade) {
        return gradeRepository.save(grade);
    }

    public void deleteById(Long id) {
        gradeRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return gradeRepository.existsById(id);
    }
}
