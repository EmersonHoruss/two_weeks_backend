package com.two_weeks_backend.two_weeks_backend.repositories.company;

import org.springframework.stereotype.Repository;

import com.two_weeks_backend.two_weeks_backend.entities.company.Operation;
import com.two_weeks_backend.two_weeks_backend.repositories.BaseRepository;

@Repository
public interface OperationRepository extends BaseRepository<Operation> {

}
