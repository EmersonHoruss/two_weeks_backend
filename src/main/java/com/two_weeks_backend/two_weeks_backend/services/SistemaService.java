package com.two_weeks_backend.two_weeks_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new RuntimeException("no encontrado"));

        return sistema.asShowDTO();
    }

    public void update(SistemaUpdateDTO sistemaUpdateDTO) {

    }
}
