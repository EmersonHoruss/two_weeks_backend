package com.two_weeks_backend.two_weeks_backend.controllers.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.size.SizeActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.size.SizeCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.size.SizeShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.size.SizeUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.product.Size;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/sizes")
public class SizeController
        extends BaseControllerImplementation<Size, SizeCreateDTO, SizeShowDTO, SizeUpdateDTO, SizeActivatedDTO> {

    @PatchMapping("")
    public ResponseEntity<ResponseDTO> setActivation(@Valid @RequestBody SizeActivatedDTO dto) {
        Size entity = this.service.update(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(entity.asShowDTO()));
    }
}
