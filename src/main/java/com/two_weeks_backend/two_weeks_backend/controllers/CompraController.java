package com.two_weeks_backend.two_weeks_backend.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.CompraEntity;

import org.springframework.beans.factory.annotation.Autowired;

import com.two_weeks_backend.two_weeks_backend.services.CompraService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/compras")
public class CompraController
        extends BaseControllerImplementation<CompraEntity, CompraCreateDTO, CompraShowDTO, CompraUpdateDTO> {
    @Autowired
    CompraService compraService;

    @Override
    @PostMapping("")
    public ResponseEntity<Object> create(@Valid @RequestBody CompraCreateDTO createDTO, UriComponentsBuilder uriBuilder,
            HttpServletRequest request) {
        createDTO.setAllCalculatedData();
        Long id = service.create(createDTO.asEntity());
        String requestUri = request.getRequestURI() + "/{id}";
        URI uri = uriBuilder.path(requestUri).buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    @PutMapping("")
    public ResponseEntity<Object> update(@Valid @RequestBody CompraUpdateDTO updateDTO) {
        updateDTO.setAllCalculatedData();
        service.update(updateDTO.asEntity());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/activacion")
    public ResponseEntity<Object> setActivated(@Valid @RequestBody CompraActivatedDTO dto) {
        compraService.setActivated(dto.asEntity());
        return ResponseEntity.ok().build();
    }
}
