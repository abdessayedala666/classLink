package com.example.backend.services;

import com.example.backend.models.ExamResult;
import com.example.backend.repository.ExamResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamResultService {

    private final ExamResultRepository examResultRepository;

    @Autowired
    public ExamResultService(ExamResultRepository examResultRepository) {
        this.examResultRepository = examResultRepository;
    }

    public List<ExamResult> findAll() {
        return examResultRepository.findAll();
    }

    public Optional<ExamResult> findById(Long id) {
        return examResultRepository.findById(id);
    }

    public List<ExamResult> findByStudentId(Long studentId) {
        return examResultRepository.findByStudentId(studentId);
    }

    public List<ExamResult> findByExamId(Long examId) {
        return examResultRepository.findByExamId(examId);
    }

    public ExamResult save(ExamResult examResult) {
        return examResultRepository.save(examResult);
    }

    public void deleteById(Long id) {
        examResultRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return examResultRepository.existsById(id);
    }
}
