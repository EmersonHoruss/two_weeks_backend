package com.two_weeks_backend.two_weeks_backend.services;

import org.springframework.stereotype.Service;

import com.two_weeks_backend.two_weeks_backend.entities.DistribuidorEntity;

@Service
public class DistribuidorService extends BaseServiceImplementation<DistribuidorEntity> {
    public void setActivated(DistribuidorEntity distribuidorEntity) {
        Long distribuidorId = distribuidorEntity.getId();

        DistribuidorEntity distribuidorEntityFound = this.get(distribuidorId);

        Boolean activated = distribuidorEntity.getActivated();
        distribuidorEntityFound.setActivated(activated);

        this.update(distribuidorEntity);
    }
}
