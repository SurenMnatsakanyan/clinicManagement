package com.example.clinicmanagement.repository;
import com.example.clinicmanagement.model.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SpecificationRepository extends JpaRepository<Specification,Long> {
    @Query("select s from Specification s where s.id = :id")
    Optional<Specification> findSpecificationById(Long id);
}
