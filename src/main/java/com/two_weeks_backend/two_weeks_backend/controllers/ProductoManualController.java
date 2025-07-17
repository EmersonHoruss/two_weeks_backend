package com.two_weeks_backend.two_weeks_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.producto_manual.ProductoManualCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.producto_manual.ProductoManualUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.services.ProductoManualService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/productos-edicion-manual")
public class ProductoManualController {
    @Autowired
    private ProductoManualService productoManualService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody ProductoManualCreateDTO productoManualCreateDTO) {
        this.productoManualService.create(productoManualCreateDTO);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody ProductoManualUpdateDTO productoManualUpdateDTO) {
        this.productoManualService.update(productoManualUpdateDTO);

        return ResponseEntity.ok().build();
    }
}
