package com.two_weeks_backend.two_weeks_backend.services.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.two_weeks_backend.two_weeks_backend.entities.management.EntityName;
import com.two_weeks_backend.two_weeks_backend.entities.management.ManagementCode;
import com.two_weeks_backend.two_weeks_backend.repositories.management.ManagementCodeRepository;

@Service
public class ManagementCodeService {
    @Autowired
    private ManagementCodeRepository managementRepository;

    public ManagementCode create(ManagementCode managementCode) {
        return this.managementRepository.save(managementCode);
    }

    public ManagementCode get(EntityName entityName) {
        return this.managementRepository.findByEntityName(entityName).orElse(null);
    }

    public ManagementCode update(ManagementCode managementCode) {
        if (managementCode.getId() == null)
            throw new IllegalArgumentException("");
        this.managementRepository.getReferenceById(managementCode.getId());
        return this.managementRepository.save(managementCode);
    }
}
