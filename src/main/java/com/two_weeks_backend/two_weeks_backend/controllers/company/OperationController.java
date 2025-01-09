package com.two_weeks_backend.two_weeks_backend.controllers.company;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.operation.OperationActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.operation.OperationCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.operation.OperationShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.operation.OperationUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.company.Operation;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/operations")
public class OperationController extends
        BaseControllerImplementation<Operation, OperationCreateDTO, OperationShowDTO, OperationUpdateDTO, OperationActivatedDTO> {
    @Override
    @PutMapping("")
    public ResponseEntity<ResponseDTO> update(@Valid @RequestBody OperationUpdateDTO dto) {
        throw new UnsupportedOperationException("Operación no soporta actualización");
    }

    @PatchMapping("")
    public ResponseEntity<ResponseDTO> setActivation(@Valid @RequestBody OperationActivatedDTO dto) {
        throw new UnsupportedOperationException("Operación no soporta eliminación");
    }
}
