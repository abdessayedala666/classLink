package com.example.backend.services;

import com.example.backend.models.SuperAdmin;
import com.example.backend.repository.SuperAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuperAdminService {

    private final SuperAdminRepository superAdminRepository;

    @Autowired
    public SuperAdminService(SuperAdminRepository superAdminRepository) {
        this.superAdminRepository = superAdminRepository;
    }

    public List<SuperAdmin> findAll() {
        return superAdminRepository.findAll();
    }

    public Optional<SuperAdmin> findById(Long id) {
        return superAdminRepository.findById(id);
    }

    public Optional<SuperAdmin> findByUserId(Long userId) {
        return superAdminRepository.findByUserId(userId);
    }

    public SuperAdmin save(SuperAdmin superAdmin) {
        return superAdminRepository.save(superAdmin);
    }

    public void deleteById(Long id) {
        superAdminRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return superAdminRepository.existsById(id);
    }
}
