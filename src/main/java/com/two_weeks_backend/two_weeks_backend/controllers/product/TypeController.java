package com.two_weeks_backend.two_weeks_backend.controllers.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.type.TypeActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.type.TypeCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.type.TypeShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.type.TypeUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.product.Type;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/types")
public class TypeController
        extends BaseControllerImplementation<Type, TypeCreateDTO, TypeShowDTO, TypeUpdateDTO, TypeActivatedDTO> {

    @PatchMapping("")
    public ResponseEntity<ResponseDTO> setActivation(@Valid @RequestBody TypeActivatedDTO dto) {
        Type entity = this.service.update(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(entity.asShowDTO()));
    }
}
