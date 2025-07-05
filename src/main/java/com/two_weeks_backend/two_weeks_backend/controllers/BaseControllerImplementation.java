package com.two_weeks_backend.two_weeks_backend.controllers;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;
import com.two_weeks_backend.two_weeks_backend.utils.specification.Specification;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("rawtypes")
public class BaseControllerImplementation<E extends BaseEntity, CreateDTO extends BaseCreateDTO<E>, ShowDTO extends BaseShowDTO<E>, UpdateDTO extends BaseUpdateDTO<E>, ActivatedDTO extends BaseActivatedDTO<E>> {
    @Autowired
    protected BaseServiceImplementation<E> service;

    @PostMapping("")
    public ResponseEntity<ResponseDTO> create(
            @Valid @RequestBody CreateDTO createDTO,
            UriComponentsBuilder uriBuilder,
            HttpServletRequest request) {
        E entity = service.create(createDTO.asEntity());
        String requestUri = request.getRequestURI() + "/{id}";
        URI uri = uriBuilder.path(requestUri).buildAndExpand(entity.getId()).toUri();
        @SuppressWarnings("unchecked")
        BaseShowDTO<E> dto = entity.asShowDTO();
        return ResponseEntity.created(uri).body(new ResponseDTO(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> get(@PathVariable Long id) {
        E entity = service.get(id);
        return ResponseEntity.ok(new ResponseDTO(entity.asShowDTO()));
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO> get(
            @RequestParam(required = false) String query,
            @PageableDefault(size = 10) Pageable pageable) {
        String decodedQuery = "";
        if (query != null) {
            try {
                decodedQuery = URLDecoder.decode(query, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        Page<E> page = service.get(new Specification<E>(decodedQuery), pageable);
        List<BaseShowDTO> dtos = page
                .getContent()
                .stream()
                .map(e -> e.asShowDTO())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseDTO(dtos, page, query));
    }

    @PutMapping("")
    public ResponseEntity<ResponseDTO> update(
            @Valid @RequestBody UpdateDTO dto) {
        E entity = service.update(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(entity.asShowDTO()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
