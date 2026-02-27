package com.example.backend.services;

import com.example.backend.models.Parent;
import com.example.backend.repository.ParentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParentService {

    private final ParentRepository parentRepository;

    public ParentService(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    public List<Parent> findAll() {
        return parentRepository.findAll();
    }

    public Optional<Parent> findById(Long id) {
        return parentRepository.findById(id);
    }

    public Optional<Parent> findByUserId(Long userId) {
        return parentRepository.findByUserId(userId);
    }

    public Parent save(Parent parent) {
        return parentRepository.save(parent);
    }

    public void deleteById(Long id) {
        parentRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return parentRepository.existsById(id);
    }
}
