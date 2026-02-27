package com.example.backend.services;

import com.example.backend.models.Assignment;
import com.example.backend.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public List<Assignment> findAll() {
        return assignmentRepository.findAll();
    }

    public Optional<Assignment> findById(Long id) {
        return assignmentRepository.findById(id);
    }

    public List<Assignment> findByClassroomId(Long classroomId) {
        return assignmentRepository.findByClassroomId(classroomId);
    }

    public List<Assignment> findByTeacherId(Long teacherId) {
        return assignmentRepository.findByTeacherId(teacherId);
    }

    public List<Assignment> findBySubjectId(Long subjectId) {
        return assignmentRepository.findBySubjectId(subjectId);
    }

    public Assignment save(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    public void deleteById(Long id) {
        assignmentRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return assignmentRepository.existsById(id);
    }
}
