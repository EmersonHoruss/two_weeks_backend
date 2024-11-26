package com.two_weeks_backend.two_weeks_backend.repositories.sell;

import org.springframework.stereotype.Repository;

import com.two_weeks_backend.two_weeks_backend.entities.sell.Customer;
import com.two_weeks_backend.two_weeks_backend.repositories.BaseRepository;

@Repository
public interface CustomerRepository extends BaseRepository<Customer>{
}
