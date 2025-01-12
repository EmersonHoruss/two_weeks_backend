package com.two_weeks_backend.two_weeks_backend.repositories.sell;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.two_weeks_backend.two_weeks_backend.entities.sell.SellPayMethod;

@Repository
public interface SellPayMethodRepository extends JpaRepository<SellPayMethod, Long> {

}
