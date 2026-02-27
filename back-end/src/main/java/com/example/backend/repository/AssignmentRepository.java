package com.example.backend.repository;

import com.example.backend.models.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByClassroomId(Long classroomId);
    List<Assignment> findByTeacherId(Long teacherId);
    List<Assignment> findBySubjectId(Long subjectId);
}
