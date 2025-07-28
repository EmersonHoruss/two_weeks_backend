package com.two_weeks_backend.two_weeks_backend.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraArrivedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.services.CompraService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/compras")
public class CompraController {
    @Autowired
    CompraService compraService;

    @PostMapping("")
    public ResponseEntity<Object> create(@Valid @RequestBody CompraCreateDTO createDTO, UriComponentsBuilder uriBuilder,
            HttpServletRequest request) {
        Long id = this.compraService.create(createDTO);
        String requestUri = request.getRequestURI() + "/{id}";
        URI uri = uriBuilder.path(requestUri).buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> get(@PathVariable Long id) {
        CompraShowDTO compraShowDTO = this.compraService.getCompraDTO(id);
        return ResponseEntity.ok(new ResponseDTO(compraShowDTO));
    }

    @PutMapping("")
    public ResponseEntity<Object> update(@Valid @RequestBody CompraUpdateDTO updateDTO) {
        this.compraService.update(updateDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/activacion")
    public ResponseEntity<Object> setActivated(@Valid @RequestBody CompraActivatedDTO dto) {
        this.compraService.setActivated(dto.asEntity());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/llego")
    public ResponseEntity<Object> setArrived(@Valid @RequestBody CompraArrivedDTO compraArrivedDTO) {
        this.compraService.setArrived(compraArrivedDTO);
        return ResponseEntity.ok().build();
    }
}
