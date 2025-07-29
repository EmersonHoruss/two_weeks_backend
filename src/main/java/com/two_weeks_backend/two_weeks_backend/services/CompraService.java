package com.two_weeks_backend.two_weeks_backend.services;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraArrivedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.detalle_compra.DetalleCompraCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.detalle_compra.DetalleCompraShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.CompraEntity;
import com.two_weeks_backend.two_weeks_backend.entities.DetalleCompraEntity;
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
        OffsetDateTime fechaCreacion = OffsetDateTime.now(ZoneOffset.UTC);
        Long distribuidorId = compraCreateDTO.getDistribuidorId();
        this.distribuidorService.isItOperative(distribuidorId);

        List<DetalleCompraEntity> detalles = compraCreateDTO.getDetalles().stream()
                .map(DetalleCompraCreateDTO::asEntity).toList();

        CompraEntity compraEntity = compraCreateDTO.asEntity();
        compraEntity.calculateTotal(detalles);
        compraEntity.setFechaCreacion(fechaCreacion);

        CompraEntity savedCompra = this.compraRepository.save(compraEntity);

        this.detalleCompraService.saveAll(savedCompra, compraCreateDTO.getDetalles(), fechaCreacion);

        return savedCompra.getId();
    }

    public CompraShowDTO getCompraDTO(Long id) {
        CompraEntity compraEntity = this.get(id);

        CompraShowDTO compraDTO = compraEntity.asShowDTO();

        List<DetalleCompraShowDTO> detalles = this.detalleCompraService.getAllByCompraId(id);
        compraDTO.setDetalles(detalles);

        return compraDTO;
    }

    @Override
    public CompraEntity get(Long id) {
        return baseRepository.findById(id).orElseThrow(() -> new RuntimeException("Compra no encontrada."));
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(CompraUpdateDTO compraUpdateDTO) {
        CompraEntity compraEntity = compraUpdateDTO.asEntity();

        if (compraEntity.getLlego()) {
            throw new RuntimeException("No se puede actualizar una compra que ya llegó.");
        }

        OffsetDateTime fechaActualizacion = OffsetDateTime.now(ZoneOffset.UTC);
        Long distribuidorId = compraEntity.getDistribuidor().getId();
        this.distribuidorService.isItOperative(distribuidorId);

        Long compraId = compraEntity.getId();
        CompraEntity compraEntityRetrieved = isItOperative(compraId);

        compraEntityRetrieved.setFecha(compraUpdateDTO.getFecha());
        compraEntityRetrieved.setFlete(compraUpdateDTO.getFlete());
        compraEntityRetrieved.setTaxi(compraUpdateDTO.getTaxi());
        compraEntityRetrieved.setOtrosGastos(compraUpdateDTO.getOtrosGastos());
        compraEntityRetrieved.setFechaActualizacion(fechaActualizacion);

        BigDecimal detallesTotal = this.detalleCompraService.saveAllAndGetTotal(compraEntityRetrieved,
                compraUpdateDTO.getDetalles(), fechaActualizacion);

        compraEntityRetrieved.calculateTotal(detallesTotal);
        this.compraRepository.save(compraEntityRetrieved);
    }

    private CompraEntity isItOperative(Long id) {
        CompraEntity compraEntityRetrieved = this.get(id);

        validateIsActivated(compraEntityRetrieved);

        return compraEntityRetrieved;
    }

    public static void validateIsActivated(CompraEntity compra) {
        Boolean isActivated = compra.getActivated();
        if (!isActivated) {
            throw new RuntimeException("La compra está desactivada.");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void setArrived(CompraArrivedDTO compraArrivedDTO) {
        if (compraArrivedDTO.getLlego() && compraArrivedDTO.getLlegoFecha() == null) {
            throw new RuntimeException("Es necesaria la fecha cuando una comprase ha marcado que ha llegado.");
        }

        Long compraId = compraArrivedDTO.getId();
        CompraEntity compraEntity = this.get(compraId);

        if (Objects.equals(compraArrivedDTO.getLlego(), compraEntity.getLlego())) // as there is no changes, so skip
            return;

        compraEntity.setLlego(compraArrivedDTO.getLlego());
        compraEntity.setLlegoFecha(compraArrivedDTO.getLlego() ? compraArrivedDTO.getLlegoFecha() : null);

        this.detalleCompraService.setArrived(compraId, compraArrivedDTO.getLlego());

        this.compraRepository.save(compraEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void setActivated(CompraEntity compraEntity) {
        // if llego throw else deactivate or activate
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        throw new RuntimeException("Funcionalidad no disponible.");
    }
}
