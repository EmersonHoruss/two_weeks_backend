package com.two_weeks_backend.two_weeks_backend.repositories.management;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.two_weeks_backend.two_weeks_backend.entities.management.EntityName;
import com.two_weeks_backend.two_weeks_backend.entities.management.ManagementCode;

@Repository
public interface ManagementCodeRepository extends JpaRepository<ManagementCode, Long> {
    Optional<ManagementCode> findByEntityName(EntityName entityName);
}
