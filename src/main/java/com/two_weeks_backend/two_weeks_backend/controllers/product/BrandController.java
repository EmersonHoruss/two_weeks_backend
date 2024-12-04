package com.two_weeks_backend.two_weeks_backend.controllers.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.brand.BrandActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.brand.BrandCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.brand.BrandShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.brand.BrandUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.product.Brand;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/brands")
public class BrandController
        extends BaseControllerImplementation<Brand, BrandCreateDTO, BrandShowDTO, BrandUpdateDTO, BrandActivatedDTO> {

    @PatchMapping("")
    public ResponseEntity<ResponseDTO> setActivation(@Valid @RequestBody BrandActivatedDTO dto) {
        Brand entity = this.service.update(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(entity.asShowDTO()));
    }
}
