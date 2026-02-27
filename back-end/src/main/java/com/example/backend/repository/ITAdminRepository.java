package com.example.backend.repository;

import com.example.backend.models.ITAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITAdminRepository extends JpaRepository<ITAdmin, Long> {
    Optional<ITAdmin> findByUserId(Long userId);
    List<ITAdmin> findBySchoolId(Long schoolId);
}
