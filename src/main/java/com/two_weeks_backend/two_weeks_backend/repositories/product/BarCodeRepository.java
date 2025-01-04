package com.two_weeks_backend.two_weeks_backend.repositories.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.two_weeks_backend.two_weeks_backend.entities.product.BarCode;

@Repository
public interface BarCodeRepository extends JpaRepository<BarCode, Long> {
    @Query("SELECT bc FROM BarCode bc WHERE bc.company.id = :companyId")
    Optional<BarCode> findFirstByCompanyId(@Param("companyId") Long companyId);
}
