package com.two_weeks_backend.two_weeks_backend.controllers.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.brand.BrandActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.brand.BrandCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.brand.BrandShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.brand.BrandUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Brand;
import com.two_weeks_backend.two_weeks_backend.services.catalog.BrandService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/brands")
public class BrandController
        extends BaseControllerImplementation<Brand, BrandCreateDTO, BrandShowDTO, BrandUpdateDTO, BrandActivatedDTO> {
    @Autowired
    private BrandService brandService;

    @PatchMapping("")
    public ResponseEntity<ResponseDTO> setActivation(@Valid @RequestBody BrandActivatedDTO dto) {
        Brand brand = this.brandService.setActivation(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(brand.asShowDTO()));
    }
}
