package com.two_weeks_backend.two_weeks_backend.services;

import org.springframework.stereotype.Service;

import com.two_weeks_backend.two_weeks_backend.entities.DistribuidorEntity;

@Service
public class DistribuidorService extends BaseServiceImplementation<DistribuidorEntity> {
    @Override
    public DistribuidorEntity get(Long id) {
        return baseRepository.findById(id).orElseThrow(() -> new RuntimeException("Distribuidor no encontrado"));
    }

    public void setActivated(DistribuidorEntity distribuidorEntity) {
        Long distribuidorId = distribuidorEntity.getId();

        DistribuidorEntity distribuidorEntityFound = this.get(distribuidorId);

        Boolean activated = distribuidorEntity.getActivated();
        distribuidorEntityFound.setActivated(activated);

        this.update(distribuidorEntity);
    }

    public void isItOperative(Long id) {
        DistribuidorEntity distribuidor = baseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Distribuidor no encontrado"));

        Boolean activated = distribuidor.getActivated();
        if (!activated) {
            throw new RuntimeException("El distribuidor " + distribuidor.getDuenio() + " está desactivado");
        }
    }
}
