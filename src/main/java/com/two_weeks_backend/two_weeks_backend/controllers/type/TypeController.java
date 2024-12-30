package com.two_weeks_backend.two_weeks_backend.controllers.type;

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

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.type.TypeActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.type.TypeCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.type.TypeShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.type.TypeUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.product.Product;
import com.two_weeks_backend.two_weeks_backend.entities.type.Type;
import com.two_weeks_backend.two_weeks_backend.exceptions.NotAllowed;
import com.two_weeks_backend.two_weeks_backend.services.product.ProductService;
import com.two_weeks_backend.two_weeks_backend.services.type.TypeService;
import com.two_weeks_backend.two_weeks_backend.utils.specification.Specification;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/types")
public class TypeController
        extends BaseControllerImplementation<Type, TypeCreateDTO, TypeShowDTO, TypeUpdateDTO, TypeActivatedDTO> {

    @Autowired
    private TypeService typeService;

    @Autowired
    private ProductService productService;

    @PatchMapping("")
    public ResponseEntity<ResponseDTO> setActivation(@Valid @RequestBody TypeActivatedDTO dto) {
        Type entity = this.typeService.setActivation(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(entity.asShowDTO()));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        String attribute = "type.id";
        String operator = "<eq>";
        String query = attribute + operator + id;
        Specification<Product> specification = new Specification<Product>(query);

        Pageable pageable = PageRequest.of(0, 1);

        Page<Product> page = this.productService.get(specification, pageable);
        if (!page.isEmpty()) {
            throw new NotAllowed(
                    "No se puede eliminar el tipo porque hay productos con ese tipo, eliminar primero todos los productos relacionados al tipo para eliminar el tipo");
        }
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
