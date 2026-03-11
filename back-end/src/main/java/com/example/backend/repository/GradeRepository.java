package com.example.backend.repository;

import com.example.backend.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findBySchoolId(Long schoolId);

    boolean existsByNameAndSchoolId(String trim, Long id);
}
