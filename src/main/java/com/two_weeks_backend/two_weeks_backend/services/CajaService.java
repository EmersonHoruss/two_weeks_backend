package com.two_weeks_backend.two_weeks_backend.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.caja.CajaCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.CajaEntity;
import com.two_weeks_backend.two_weeks_backend.repositories.CajaRepository;

@Service
public class CajaService {
    @Autowired
    private CajaRepository cajaRepository;

    @Transactional(rollbackFor = Exception.class)
    public Long create(CajaCreateDTO cajaCreateDTO) {
        CajaEntity cajaEntity = cajaCreateDTO.asEntity();
        cajaEntity.setMontoFinalFisico(BigDecimal.ZERO);
        cajaEntity.setMontoFinalDigital(BigDecimal.ZERO);
        cajaEntity.setGanancia(BigDecimal.ZERO);

        CajaEntity cajaEntitySaved = this.cajaRepository.save(cajaEntity);

        Long cajaId = cajaEntitySaved.getId();

        return cajaId;
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(CajaEntity caja) {
        CajaEntity retrievedCaja = this.get(caja.getId());

        retrievedCaja.checkIfPossibleUpdating();
        retrievedCaja.setMontoInicial(caja.getMontoInicial());
        retrievedCaja.calculateGanancia();
        retrievedCaja.setFecha(caja.getFecha());

        this.cajaRepository.save(retrievedCaja);
    }

    public CajaEntity get(Long id) {
        return this.cajaRepository.findById(id).orElseThrow(() -> new RuntimeException("caja no encontrada"));
    }

    public Page<CajaEntity> get(Pageable pageable) {
        return this.cajaRepository.findAll(pageable);
    }
}