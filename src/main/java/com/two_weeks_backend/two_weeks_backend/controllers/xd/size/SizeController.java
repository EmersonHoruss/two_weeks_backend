package com.two_weeks_backend.two_weeks_backend.controllers.size;

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

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.size.SizeActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.size.SizeCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.size.SizeShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.size.SizeUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.product.Product;
import com.two_weeks_backend.two_weeks_backend.entities.size.Size;
import com.two_weeks_backend.two_weeks_backend.exceptions.NotAllowed;
import com.two_weeks_backend.two_weeks_backend.services.product.ProductService;
import com.two_weeks_backend.two_weeks_backend.services.size.SizeService;
import com.two_weeks_backend.two_weeks_backend.utils.specification.Specification;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/sizes")
public class SizeController
        extends BaseControllerImplementation<Size, SizeCreateDTO, SizeShowDTO, SizeUpdateDTO, SizeActivatedDTO> {
    @Autowired
    SizeService sizeService;

    @Autowired
    private ProductService productService;

    @PatchMapping("")
    public ResponseEntity<ResponseDTO> setActivation(@Valid @RequestBody SizeActivatedDTO dto) {
        Size entity = this.sizeService.setActivation(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(entity.asShowDTO()));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        String attribute = "size.id";
        String operator = "<eq>";
        String query = attribute + operator + id;
        Specification<Product> specification = new Specification<Product>(query);

        Pageable pageable = PageRequest.of(0, 1);

        Page<Product> page = this.productService.get(specification, pageable);
        if (!page.isEmpty()) {
            throw new NotAllowed(
                    "Operación fallida: Necesitas eliminar todos los productos relacionados a la talla a eliminar");
        }

        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
