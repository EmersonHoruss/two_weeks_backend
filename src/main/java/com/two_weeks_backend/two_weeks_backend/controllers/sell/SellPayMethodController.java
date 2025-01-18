package com.two_weeks_backend.two_weeks_backend.controllers.sell;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_pay_method.SellPayMethodActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_pay_method.SellPayMethodCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_pay_method.SellPayMethodShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_pay_method.SellPayMethodUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellPayMethod;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/sell_pay_methods")
public class SellPayMethodController extends
        BaseControllerImplementation<SellPayMethod, SellPayMethodCreateDTO, SellPayMethodShowDTO, SellPayMethodUpdateDTO, SellPayMethodActivatedDTO> {

}
