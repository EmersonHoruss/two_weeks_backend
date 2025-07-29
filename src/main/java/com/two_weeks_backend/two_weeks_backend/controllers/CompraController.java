package com.two_weeks_backend.two_weeks_backend.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraArrivedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra.CompraUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.entities.CompraEntity;
import com.two_weeks_backend.two_weeks_backend.services.CompraService;
import com.two_weeks_backend.two_weeks_backend.utils.specification.Specification;

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

    @GetMapping("")
    public ResponseEntity<ResponseDTO> get(@RequestParam(required = false) String query,
            @PageableDefault(size = 10) Pageable pageable) {
        String decodedQuery = "";
        if (query != null) {
            try {
                decodedQuery = URLDecoder.decode(query, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return ResponseEntity.badRequest().build();
            }
        }

        Page<CompraEntity> page = this.compraService.get(new Specification<>(decodedQuery), pageable);
        List<CompraShowDTO> dtos = page.getContent().stream().map(e -> e.asShowDTO()).collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseDTO(dtos, page, query));
    }

    @PutMapping("")
    public ResponseEntity<Object> update(@Valid @RequestBody CompraUpdateDTO updateDTO) {
        this.compraService.update(updateDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/llego")
    public ResponseEntity<Object> setArrived(@Valid @RequestBody CompraArrivedDTO compraArrivedDTO) {
        this.compraService.setArrived(compraArrivedDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/activacion")
    public ResponseEntity<Object> setActivated(@Valid @RequestBody CompraActivatedDTO dto) {
        this.compraService.setActivated(dto);
        return ResponseEntity.ok().build();
    }
}
