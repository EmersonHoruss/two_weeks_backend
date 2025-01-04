package com.two_weeks_backend.two_weeks_backend.controllers.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.operation.OperationCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.operation.OperationShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.entities.product.Operation;
import com.two_weeks_backend.two_weeks_backend.exceptions.NotImplemented;
import com.two_weeks_backend.two_weeks_backend.services.product.OperationService;
import com.two_weeks_backend.two_weeks_backend.utils.specification.Specification;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.net.URI;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/operations")
public class OperationController {
    @Autowired
    OperationService operationService;

    @PostMapping("")
    public ResponseEntity<ResponseDTO> create(@Valid @RequestBody OperationCreateDTO createDTO,
            UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        Operation entity = operationService.create(createDTO);
        String requestUri = request.getRequestURI() + "/{id}";
        URI uri = uriBuilder.path(requestUri).buildAndExpand(entity.getId()).toUri();
        OperationShowDTO dto = entity.asShowDTO();
        return ResponseEntity.created(uri).body(new ResponseDTO(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> get(@PathVariable Long id) {
        Operation entity = operationService.get(id);
        return ResponseEntity.ok(new ResponseDTO(entity.asShowDTO()));
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO> get(
            @RequestParam(required = false) String query,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<Operation> page = operationService.get(new Specification<Operation>(query), pageable);
        List<OperationShowDTO> dtos = page
                .getContent()
                .stream()
                .map(e -> e.asShowDTO())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseDTO(dtos, page, query));
    }

    @PutMapping("")
    public Object update(Object obj) {
        throw new NotImplemented("El método \"actualizar\" no está soportado por la entidad Operación.");
    }

    @PatchMapping("")
    public Object setActivation(Object obj) {
        throw new NotImplemented("El método \"eliminar lógicamente\" no está soportado por la entidad Operación.");
    }

    @DeleteMapping("")
    public Object delete(Object obj) {
        throw new NotImplemented("El método \"eliminar físicamente\" no está soportado por la entidad Operación.");
    }
}
