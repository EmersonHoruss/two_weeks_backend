package com.two_weeks_backend.two_weeks_backend.controllers.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.purchase.product_provider.ProductProviderActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.purchase.product_provider.ProductProviderCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.purchase.product_provider.ProductProviderShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.purchase.product_provider.ProductProviderUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.purchase.ProductProvider;
import com.two_weeks_backend.two_weeks_backend.services.purchase.ProductProviderService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/products_of_provider")
public class ProductProviderController extends
        BaseControllerImplementation<ProductProvider, ProductProviderCreateDTO, ProductProviderShowDTO, ProductProviderUpdateDTO, ProductProviderActivatedDTO> {
    @Autowired
    private ProductProviderService productProviderService;

    @PatchMapping("")
    public ResponseEntity<ResponseDTO> setActivation(@Valid @RequestBody ProductProviderActivatedDTO dto) {
        ProductProvider productProvider = this.productProviderService.setActivation(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(productProvider.asShowDTO()));
    }
}
