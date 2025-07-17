package com.two_weeks_backend.two_weeks_backend.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.caja.CajaShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.caja.CajaUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.entities.CajaEntity;
import com.two_weeks_backend.two_weeks_backend.services.CajaService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/cajas")
public class CajaController {
    @Autowired
    private CajaService cajaService;

    @PutMapping("")
    public ResponseEntity<Object> update(@Valid @RequestBody CajaUpdateDTO dto) {
        cajaService.update(dto.asEntity());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> get(@PathVariable Long id) {
        CajaEntity entity = this.cajaService.get(id);
        return ResponseEntity.ok(new ResponseDTO(entity.asShowDTO()));
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO> get(@PageableDefault(size = 10) Pageable pageable) {
        Page<CajaEntity> page = this.cajaService.get(pageable);
        List<CajaShowDTO> dtos = page.getContent().stream().map(e -> e.asShowDTO()).collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseDTO(dtos, page, ""));
    }
}
