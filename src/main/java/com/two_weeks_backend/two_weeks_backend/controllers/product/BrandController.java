package com.two_weeks_backend.two_weeks_backend.controllers.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.two_weeks_backend.two_weeks_backend.entities.product.Product;
import com.two_weeks_backend.two_weeks_backend.exceptions.NotAllowed;
import com.two_weeks_backend.two_weeks_backend.services.product.BrandService;
import com.two_weeks_backend.two_weeks_backend.services.product.ProductService;
import com.two_weeks_backend.two_weeks_backend.utils.specification.Specification;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/brands")
public class BrandController
        extends BaseControllerImplementation<Brand, BrandCreateDTO, BrandShowDTO, BrandUpdateDTO, BrandActivatedDTO> {
    @Autowired
    BrandService brandService;

    @Autowired
    private ProductService productService;

    @PatchMapping("")
    public ResponseEntity<ResponseDTO> setActivation(@Valid @RequestBody BrandActivatedDTO dto) {
        Brand entity = this.brandService.setActivation(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(entity.asShowDTO()));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        String attribute = "brand.id";
        String operator = "<eq>";
        String query = attribute + operator + id;
        Specification<Product> specification = new Specification<Product>(query);

        Pageable pageable = PageRequest.of(0, 1);

        Page<Product> page = this.productService.get(specification, pageable);
        if (!page.isEmpty()) {
            throw new NotAllowed(
                    "No se puede eliminar la marca porque hay productos con esa marca, eliminar primero todos los productos relacionados a la marca para eliminar la marca");
        }
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
