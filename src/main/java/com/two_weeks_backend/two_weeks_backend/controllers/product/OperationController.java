package com.two_weeks_backend.two_weeks_backend.controllers.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.operation.OperationActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.operation.OperationCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.operation.OperationShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.operation.OperationUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.product.Operation;

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
