package com.two_weeks_backend.two_weeks_backend.services;

import com.two_weeks_backend.two_weeks_backend.entities.CajaEntity;
import com.two_weeks_backend.two_weeks_backend.repositories.CajaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CajaService {
    @Autowired
    private CajaRepository cajaRepository;
    
    @Transactional(rollbackFor = Exception.class)
    public CajaEntity update(CajaEntity caja) {
        CajaEntity retrievedCaja = this.get(caja.getId());
        
        retrievedCaja.checkIfPossibleUpdating();
        retrievedCaja.setMontoInicial(caja.getMontoInicial());
        retrievedCaja.calculateGanancia();

        return this.cajaRepository.save(retrievedCaja);
    }

    public CajaEntity get(Long id) {
        return this.cajaRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("caja no encontrada"));
    }

    public Page<CajaEntity> get(Pageable pageable) {
        return this.cajaRepository.findAll(pageable);
    }
}