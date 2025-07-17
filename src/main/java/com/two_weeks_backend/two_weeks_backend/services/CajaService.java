package com.two_weeks_backend.two_weeks_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.entities.CajaEntity;
import com.two_weeks_backend.two_weeks_backend.repositories.CajaRepository;

@Service
public class CajaService {
    @Autowired
    private CajaRepository cajaRepository;

    @Transactional(rollbackFor = Exception.class)
    public void update(CajaEntity caja) {
        CajaEntity retrievedCaja = this.get(caja.getId());

        retrievedCaja.checkIfPossibleUpdating();
        retrievedCaja.setMontoInicial(caja.getMontoInicial());
        retrievedCaja.calculateGanancia();

        this.cajaRepository.save(retrievedCaja);
    }

    public CajaEntity get(Long id) {
        return this.cajaRepository.findById(id).orElseThrow(() -> new RuntimeException("caja no encontrada"));
    }

    public Page<CajaEntity> get(Pageable pageable) {
        return this.cajaRepository.findAll(pageable);
    }
}