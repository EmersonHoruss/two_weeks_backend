package com.two_weeks_backend.two_weeks_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.detalle_compra.DetalleCompraCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.CompraEntity;

@Service
public class CompraService extends BaseServiceImplementation<CompraEntity> {
    @Autowired
    DetalleCompraService detalleCompraService;

    @Autowired
    DistribuidorService distribuidorService;

    @Transactional(rollbackFor = Exception.class)
    public Long create(CompraCreateDTO compraCreateDTO) {
        Long distribuidorId = compraCreateDTO.getDistribuidorId();
        this.distribuidorService.isItOperative(distribuidorId);

        compraCreateDTO.setAllCalculatedData();
        CompraEntity savedCompra = this.baseRepository.save(compraCreateDTO.asEntity());

        this.detalleCompraService.saveAll(savedCompra, compraCreateDTO.getDetalles());

        return savedCompra.getId();
    }

    // @Override
    // public CompraEntity get(Long id) {

    // }

    // @Override
    // @Transactional(rollbackFor = Exception.class)
    // public void update(CompraEntity compraEntity) {
    // }

    // @Override
    // @Transactional(rollbackFor = Exception.class)
    // public void delete(Long id) {

    // }

    // public void setActivated(CompraEntity compraEntity) {

    // }
}
