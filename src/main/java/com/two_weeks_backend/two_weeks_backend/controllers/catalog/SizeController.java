package com.two_weeks_backend.two_weeks_backend.controllers.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.size.SizeActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.size.SizeCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.size.SizeShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.size.SizeUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Size;
import com.two_weeks_backend.two_weeks_backend.services.catalog.SizeService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/sizes")
public class SizeController
        extends BaseControllerImplementation<Size, SizeCreateDTO, SizeShowDTO, SizeUpdateDTO, SizeActivatedDTO> {
    @Autowired
    private SizeService sizeService;

    @PatchMapping("")
    public ResponseEntity<ResponseDTO> setActivation(@Valid @RequestBody SizeActivatedDTO dto) {
        Size size = this.sizeService.setActivation(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(size.asShowDTO()));
    }
}
