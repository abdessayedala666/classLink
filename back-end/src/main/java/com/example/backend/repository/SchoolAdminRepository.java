package com.example.backend.repository;

import com.example.backend.models.SchoolAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolAdminRepository extends JpaRepository<SchoolAdmin, Long> {
    Optional<SchoolAdmin> findByUserId(Long userId);
    List<SchoolAdmin> findBySchoolId(Long schoolId);
}
