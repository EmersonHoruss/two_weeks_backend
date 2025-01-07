package com.two_weeks_backend.two_weeks_backend.controllers.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.type.TypeActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.type.TypeCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.type.TypeShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.type.TypeUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Type;
import com.two_weeks_backend.two_weeks_backend.services.catalog.TypeService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/types")
public class TypeController
        extends BaseControllerImplementation<Type, TypeCreateDTO, TypeShowDTO, TypeUpdateDTO, TypeActivatedDTO> {
    @Autowired
    private TypeService typeService;

    @PatchMapping("")
    public ResponseEntity<ResponseDTO> setActivation(@Valid @RequestBody TypeActivatedDTO dto) {
        Type type = this.typeService.setActivation(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(type.asShowDTO()));
    }
}
