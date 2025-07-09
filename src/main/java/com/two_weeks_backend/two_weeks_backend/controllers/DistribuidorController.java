package com.two_weeks_backend.two_weeks_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.distribuidor.DistribuidorActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.distribuidor.DistribuidorCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.distribuidor.DistribuidorShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.distribuidor.DistribuidorUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.DistribuidorEntity;
import com.two_weeks_backend.two_weeks_backend.services.DistribuidorService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/distribuidores")
public class DistribuidorController extends
        BaseControllerImplementation<DistribuidorEntity, DistribuidorCreateDTO, DistribuidorShowDTO, DistribuidorUpdateDTO> {
    @Autowired
    DistribuidorService distribuidorService;

    @PutMapping("/activated")
    public ResponseEntity<Object> setActivated(@Valid @RequestBody DistribuidorActivatedDTO dto) {
        distribuidorService.setActivated(dto.asEntity());
        return ResponseEntity.ok().build();
    }
}
