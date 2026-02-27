package com.example.backend.repository;

import com.example.backend.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByClassroomId(Long classroomId);
    List<Exam> findByTeacherId(Long teacherId);
    List<Exam> findBySubjectId(Long subjectId);
}
