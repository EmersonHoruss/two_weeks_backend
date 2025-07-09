package com.two_weeks_backend.two_weeks_backend.services;

import org.springframework.stereotype.Service;

import com.two_weeks_backend.two_weeks_backend.entities.ProductoEntity;

@Service
public class ProductoService extends BaseServiceImplementation<ProductoEntity> {

    public void isItOperative(Long id) {
        ProductoEntity producto = baseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("producto no encontrado"));

        Boolean activated = producto.getActivated();
        if (!activated) {
            throw new RuntimeException("El producto " + producto.getNombre() + " está desactivado");
        }
    }
}
