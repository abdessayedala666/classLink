package com.example.backend.services;

import com.example.backend.models.School;
import com.example.backend.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
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
