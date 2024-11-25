package com.two_weeks_backend.two_weeks_backend.controllers;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.operation.OperationActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.operation.OperationCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.operation.OperationShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.operation.OperationUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.Operation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/operations")
public class OperationController extends BaseControllerImplementation<
    Operation,
    OperationCreateDTO,
    OperationShowDTO,
    OperationUpdateDTO,
    OperationActivatedDTO
>{
}
