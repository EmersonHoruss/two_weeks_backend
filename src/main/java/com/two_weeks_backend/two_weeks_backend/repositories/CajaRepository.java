package com.two_weeks_backend.two_weeks_backend.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.two_weeks_backend.two_weeks_backend.entities.CajaEntity;

@Repository
public interface CajaRepository extends JpaRepository<CajaEntity, Long> {}
