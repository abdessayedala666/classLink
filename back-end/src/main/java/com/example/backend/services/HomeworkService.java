package com.example.backend.services;

import com.example.backend.models.Homework;
import com.example.backend.repository.HomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeworkService {

    private final HomeworkRepository homeworkRepository;

    @Autowired
    public HomeworkService(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    public List<Homework> findAll() {
        return homeworkRepository.findAll();
    }

    public Optional<Homework> findById(Long id) {
        return homeworkRepository.findById(id);
    }

    public List<Homework> findByStudentId(Long studentId) {
        return homeworkRepository.findByStudentId(studentId);
    }

    public List<Homework> findByAssignmentId(Long assignmentId) {
        return homeworkRepository.findByAssignmentId(assignmentId);
    }

    public Homework save(Homework homework) {
        return homeworkRepository.save(homework);
    }

    public void deleteById(Long id) {
        homeworkRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return homeworkRepository.existsById(id);
    }
}
