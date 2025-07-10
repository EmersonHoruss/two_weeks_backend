package com.two_weeks_backend.two_weeks_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.detalle_compra.DetalleCompraShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.CompraEntity;
import com.two_weeks_backend.two_weeks_backend.repositories.CompraRepository;

@Service
public class CompraService extends BaseServiceImplementation<CompraEntity> {
    @Autowired
    CompraRepository compraRepository;

    @Autowired
    DetalleCompraService detalleCompraService;

    @Autowired
    DistribuidorService distribuidorService;

    @Transactional(rollbackFor = Exception.class)
    public Long create(CompraCreateDTO compraCreateDTO) {
        Long distribuidorId = compraCreateDTO.getDistribuidorId();
        this.distribuidorService.isItOperative(distribuidorId);

        compraCreateDTO.setAllCalculatedData();
        CompraEntity savedCompra = this.compraRepository.save(compraCreateDTO.asEntity());

        this.detalleCompraService.saveAll(savedCompra, compraCreateDTO.getDetalles());

        return savedCompra.getId();
    }

    public CompraShowDTO getCompraDTO(Long id) {
        CompraEntity compraEntity = this.compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("compra no encontrada"));

        CompraShowDTO compraDTO = compraEntity.asShowDTO();

        List<DetalleCompraShowDTO> detalles = this.detalleCompraService.getAllByCompraId(id);
        compraDTO.setDetalles(detalles);

        return compraDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(CompraUpdateDTO compraUpdateDTO) {
        Long distribuidorId = compraUpdateDTO.getDistribuidorId();
        this.distribuidorService.isItOperative(distribuidorId);

        
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {

    }

    public void setActivated(CompraEntity compraEntity) {

    }
}
