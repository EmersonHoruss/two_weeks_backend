package com.two_weeks_backend.two_weeks_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sistema.SistemaEdicionManualProductoDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sistema.SistemaShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sistema.SistemaUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.SistemaEntity;
import com.two_weeks_backend.two_weeks_backend.repositories.SistemaRepository;

@Service
public class SistemaService {
    @Autowired
    SistemaRepository sistemaRepository;

    public SistemaShowDTO get(Long id) {
        SistemaEntity sistema = this.sistemaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("sistema no encontrado"));

        return sistema.asShowDTO();
    }

    public void update(SistemaUpdateDTO sistemaUpdateDTO) {
        Long id = sistemaUpdateDTO.getId();
        SistemaEntity sistema = this.sistemaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("sistema no encontrado"));

        sistema.setEmpresaNombre(sistemaUpdateDTO.getEmpresaNombre());
        sistema.setEmpresaRuc(sistemaUpdateDTO.getEmpresaRuc());
        sistema.setEmpresaDuenio(sistemaUpdateDTO.getEmpresaDuenio());
        sistema.setDuenioCelular(sistemaUpdateDTO.getDuenioCelular());
        sistema.setAbre(sistemaUpdateDTO.getAbre());
        sistema.setCierra(sistemaUpdateDTO.getCierra());

        this.sistemaRepository.save(sistema);
    }

    public void setEdicionManualProducto(SistemaEdicionManualProductoDTO edicionManualProductoDTO) {
        Long id = edicionManualProductoDTO.getId();
        SistemaEntity sistema = this.sistemaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("sistema no encontrado"));

        sistema.setEdicionManualProducto(edicionManualProductoDTO.getEdicionManualProducto());

        this.sistemaRepository.save(sistema);
    }
}
