package com.two_weeks_backend.two_weeks_backend.controllers.sell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.customer.CustomerActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.customer.CustomerCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.customer.CustomerShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.customer.CustomerUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Customer;

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
