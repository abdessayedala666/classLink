package com.example.backend.services;

import com.example.backend.models.Classroom;
import com.example.backend.repository.ClassroomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    
    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
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
