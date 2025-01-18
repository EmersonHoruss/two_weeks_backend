package com.two_weeks_backend.two_weeks_backend.controllers.sell;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_returning.SellReturningActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_returning.SellReturningCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_returning.SellReturningShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_returning.SellReturningUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellReturning;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/sell_returnings")
public class SellReturningController extends
        BaseControllerImplementation<SellReturning, SellReturningCreateDTO, SellReturningShowDTO, SellReturningUpdateDTO, SellReturningActivatedDTO> {

}
