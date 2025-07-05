package com.two_weeks_backend.two_weeks_backend.controllers;

import com.two_weeks_backend.two_weeks_backend.entities.CajaEntity;
import com.two_weeks_backend.two_weeks_backend.services.CajaService;
import com.two_weeks_backend.two_weeks_backend.DTOs.caja.CajaUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.caja.CajaShowDTO;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/cajas")
public class CajaController {
    @Autowired
    private CajaService cajaService;

    @PutMapping("")
    public ResponseEntity<ResponseDTO> update(
        @Valid @RequestBody CajaUpdateDTO dto
    ){
        CajaEntity cajaEntity = cajaService.update(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(cajaEntity.asShowDTO()))
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> get(@PathVariable Long id) {
        CajaEntity entity = this.cajaService.get(id);
        return ResponseEntity.ok(new ResponseDTO(entity.asShowDTO()));
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO> get(
        @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<CajaEntity> page = this.cajaService.get(pageable);
        List<CajaShowDTO> dtos = page
            .getContent()
            .stream()
            .map(e -> e.asShowDTO())
            .collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseDTO(dtos, page, ""));
    }
}
