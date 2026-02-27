package com.example.backend.services;

import com.example.backend.models.Exam;
import com.example.backend.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    private final ExamRepository examRepository;

    @Autowired
    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public List<Exam> findAll() {
        return examRepository.findAll();
    }

    public Optional<Exam> findById(Long id) {
        return examRepository.findById(id);
    }

    public List<Exam> findByClassroomId(Long classroomId) {
        return examRepository.findByClassroomId(classroomId);
    }

    public List<Exam> findByTeacherId(Long teacherId) {
        return examRepository.findByTeacherId(teacherId);
    }

    public List<Exam> findBySubjectId(Long subjectId) {
        return examRepository.findBySubjectId(subjectId);
    }

    public Exam save(Exam exam) {
        return examRepository.save(exam);
    }

    public void deleteById(Long id) {
        examRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return examRepository.existsById(id);
    }
}
