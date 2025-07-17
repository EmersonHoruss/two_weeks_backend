package com.two_weeks_backend.two_weeks_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sistema.SistemaEdicionManualProductoDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sistema.SistemaShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sistema.SistemaUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.services.SistemaService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/sistemas")
public class SistemaController {
        @Autowired
        SistemaService sistemaService;

        @GetMapping("/{id}")
        public ResponseEntity<ResponseDTO> get(@PathVariable Long id) {
                SistemaShowDTO sistemaShowDTO = this.sistemaService.get(id);

                return ResponseEntity.ok(new ResponseDTO(sistemaShowDTO));
        }

        @PutMapping("")
        public ResponseEntity<Object> update(@Valid @RequestBody SistemaUpdateDTO sistemaUpdateDTO) {
                this.sistemaService.update(sistemaUpdateDTO);

                return ResponseEntity.ok().build();
        }

        @PutMapping("/edicion-manual-producto")
        public ResponseEntity<Object> setEdicionManualProducto(
                        @Valid @RequestBody SistemaEdicionManualProductoDTO edicionManualProductoDTO) {
                this.sistemaService.setEdicionManualProducto(edicionManualProductoDTO);

                return ResponseEntity.ok().build();
        }
}
