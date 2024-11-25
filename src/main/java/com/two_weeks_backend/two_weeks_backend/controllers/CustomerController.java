package com.two_weeks_backend.two_weeks_backend.controllers;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.customer.CustomerActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.customer.CustomerCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.customer.CustomerShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.customer.CustomerUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.Customer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/customers")
public class CustomerController extends BaseControllerImplementation<
    Customer,
    CustomerCreateDTO,
    CustomerShowDTO,
    CustomerUpdateDTO,
    CustomerActivatedDTO
>{
}
