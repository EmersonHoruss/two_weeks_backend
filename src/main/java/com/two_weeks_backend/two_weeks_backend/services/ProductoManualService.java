package com.two_weeks_backend.two_weeks_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.producto_manual.ProductoManualCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.producto_manual.ProductoManualUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.ProductoEntity;
import com.two_weeks_backend.two_weeks_backend.repositories.ProductoManualRepository;

@Service
public class ProductoManualService {
    @Autowired
    private ProductoManualRepository productoManualRepository;

    @Autowired
    private SistemaService sistemaService;

    @Transactional(rollbackFor = Exception.class)
    public void create(ProductoManualCreateDTO productoManualCreateDTO) {
        Long sistemaId = productoManualCreateDTO.getSistemaId();
        sistemaService.validateExistence(sistemaId);

        ProductoEntity producto = productoManualCreateDTO.asEntity();

        this.productoManualRepository.save(producto);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(ProductoManualUpdateDTO productoManualUpdateDTO) {
        Long sistemaId = productoManualUpdateDTO.getSistemaId();
        sistemaService.validateExistence(sistemaId);

        Long id = productoManualUpdateDTO.getId();
        this.productoManualRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        ProductoEntity productoToSave = productoManualUpdateDTO.asEntity();

        this.productoManualRepository.save(productoToSave);
    }
}
