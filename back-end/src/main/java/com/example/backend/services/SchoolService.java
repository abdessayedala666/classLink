package com.example.backend.services;

import com.example.backend.dto.school.SchoolDTO;
import com.example.backend.exceptions.SchoolNotFoundException;
import com.example.backend.models.School;
import com.example.backend.models.SchoolAdmin;
import com.example.backend.repository.SchoolAdminRepository;
import com.example.backend.repository.SchoolRepository;
import com.example.backend.security.CustomUserDetails;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;
     private final SpiceDBAuthorizationService authorizationService;
     private final SchoolAdminRepository schoolAdminRepository ;


    public SchoolService(SchoolRepository schoolRepository, SpiceDBAuthorizationService authorizationService, SchoolAdminRepository schoolAdminRepository) {
        this.schoolRepository = schoolRepository;
        this.authorizationService = authorizationService;
        this.schoolAdminRepository = schoolAdminRepository;
    }

    public SchoolDTO getSchoolByAdmin(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication() ;
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal() ;
        String email = userDetails.getEmail() ;
        SchoolAdmin admin =  schoolAdminRepository.findByUserEmail(email)
                .orElseThrow(() -> new SchoolNotFoundException("school admin not found for email: " + email));
        School school = admin.getSchool() ;
        if (school == null){
            throw new SchoolNotFoundException("school not found for admin with email: " + email);
        }

        return new SchoolDTO(school);
    }

    public List<School> findAll() {
        return schoolRepository.findAll();
    }

    public Optional<School> findById(Long id) {
        return schoolRepository.findById(id);
    }

    public Optional<School> findByName(String name) {
        return schoolRepository.findByName(name);
    }

    public School save(School school) {
        return schoolRepository.save(school);
    }

    public void deleteById(Long id) {
        schoolRepository.deleteById(id);
    }

    public boolean existsByName(String name) {
        return schoolRepository.existsByName(name);
    }

    public boolean existsById(Long id) {
        return schoolRepository.existsById(id);
    }
}
