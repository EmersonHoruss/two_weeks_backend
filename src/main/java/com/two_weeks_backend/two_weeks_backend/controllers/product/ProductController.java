package com.two_weeks_backend.two_weeks_backend.controllers.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.product.ProductActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.product.ProductCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.product.ProductShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.product.ProductUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.product.Product;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/products")
public class ProductController extends
        BaseControllerImplementation<Product, ProductCreateDTO, ProductShowDTO, ProductUpdateDTO, ProductActivatedDTO> {

    @PatchMapping("")
    public ResponseEntity<ResponseDTO> setActivation(
            @Valid @RequestBody ProductActivatedDTO dto) {
        Product entity = this.service.update(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(entity.asShowDTO()));
    }
}
