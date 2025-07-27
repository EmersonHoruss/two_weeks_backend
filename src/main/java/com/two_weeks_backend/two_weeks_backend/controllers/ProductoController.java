package com.two_weeks_backend.two_weeks_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.producto.ProductoActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.producto.ProductoCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.producto.ProductoShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.producto.ProductoUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.ProductoEntity;
import com.two_weeks_backend.two_weeks_backend.services.ProductoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/productos")
public class ProductoController
        extends BaseControllerImplementation<ProductoEntity, ProductoCreateDTO, ProductoShowDTO, ProductoUpdateDTO> {
    @Autowired
    private ProductoService productoService;

    @Override
    @PostMapping("")
    public ResponseEntity<Object> create(@Valid @RequestBody ProductoCreateDTO createDTO,
            UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        Long id = this.productoService.create(createDTO);
        String requestUri = request.getRequestURI() + "/{id}";
        URI uri = uriBuilder.path(requestUri).buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    @PutMapping("")
    public ResponseEntity<Object> update(@Valid @RequestBody ProductoUpdateDTO updateDTO) {
        this.productoService.update(updateDTO);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        throw new RuntimeException("Eliminación de producto no disponible.");
        // for the moment to do not waste time i will avoid this feature
        // this.productoService.delete(id);
        // return ResponseEntity.noContent().build();
    }

    @PutMapping("/activacion")
    public ResponseEntity<Object> setActivated(@Valid @RequestBody ProductoActivatedDTO dto) {
        this.productoService.setActivated(dto);
        return ResponseEntity.ok().build();
    }
}
