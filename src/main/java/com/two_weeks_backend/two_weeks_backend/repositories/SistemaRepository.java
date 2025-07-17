package com.two_weeks_backend.two_weeks_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.two_weeks_backend.two_weeks_backend.entities.SistemaEntity;

@Repository
public interface SistemaRepository extends JpaRepository<SistemaEntity, Long> {
}
