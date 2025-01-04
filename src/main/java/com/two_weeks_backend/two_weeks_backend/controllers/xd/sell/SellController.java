package com.two_weeks_backend.two_weeks_backend.controllers.sell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell.SellActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell.SellCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell.SellShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell.SellUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Sell;

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
