package com.example.backend.services;

import com.example.backend.models.ITAdmin;
import com.example.backend.repository.ITAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ITAdminService {

    private final ITAdminRepository itAdminRepository;

    @Autowired
    public ITAdminService(ITAdminRepository itAdminRepository) {
        this.itAdminRepository = itAdminRepository;
    }

    public List<ITAdmin> findAll() {
        return itAdminRepository.findAll();
    }

    public Optional<ITAdmin> findById(Long id) {
        return itAdminRepository.findById(id);
    }

    public Optional<ITAdmin> findByUserId(Long userId) {
        return itAdminRepository.findByUserId(userId);
    }

    public List<ITAdmin> findBySchoolId(Long schoolId) {
        return itAdminRepository.findBySchoolId(schoolId);
    }

    public ITAdmin save(ITAdmin itAdmin) {
        return itAdminRepository.save(itAdmin);
    }

    public void deleteById(Long id) {
        itAdminRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return itAdminRepository.existsById(id);
    }
}
