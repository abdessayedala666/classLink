package com.example.backend.repository;

import com.example.backend.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByClassroomId(Long classroomId);
    Optional<Subject> findByTeacherId(Long teacherId);
}
