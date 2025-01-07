package com.two_weeks_backend.two_weeks_backend.controllers.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.product_company.ProductCompanyActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.product_company.ProductCompanyCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.product_company.ProductCompanyShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.product_company.ProductCompanyUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.company.ProductCompany;
import com.two_weeks_backend.two_weeks_backend.services.company.ProductCompanyService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/products_of_company")
public class ProductCompanyController extends
        BaseControllerImplementation<ProductCompany, ProductCompanyCreateDTO, ProductCompanyShowDTO, ProductCompanyUpdateDTO, ProductCompanyActivatedDTO> {
    @Autowired
    private ProductCompanyService productCompanyService;

    @PatchMapping("")
    public ResponseEntity<ResponseDTO> setActivation(@Valid @RequestBody ProductCompanyActivatedDTO dto) {
        ProductCompany productCompany = this.productCompanyService.setActivation(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(productCompany.asShowDTO()));
    }
}
