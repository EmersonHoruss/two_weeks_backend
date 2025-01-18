package com.two_weeks_backend.two_weeks_backend.controllers.sell;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_change.SellChangeActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_change.SellChangeCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_change.SellChangeShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_change.SellChangeUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellChange;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/sell_changes")
public class SellChangeController extends
        BaseControllerImplementation<SellChange, SellChangeCreateDTO, SellChangeShowDTO, SellChangeUpdateDTO, SellChangeActivatedDTO> {

}
