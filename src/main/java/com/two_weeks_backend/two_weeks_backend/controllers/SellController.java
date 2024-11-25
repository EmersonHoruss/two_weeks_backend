package com.two_weeks_backend.two_weeks_backend.controllers;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.SellActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.SellCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.SellShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.SellUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.Sell;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/sells")
public class SellController extends BaseControllerImplementation<
    Sell,
    SellCreateDTO,
    SellShowDTO,
    SellUpdateDTO,
    SellActivatedDTO
>{
}
