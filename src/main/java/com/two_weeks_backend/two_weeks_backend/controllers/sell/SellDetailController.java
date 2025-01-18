package com.two_weeks_backend.two_weeks_backend.controllers.sell;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_detail.SellDetailActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_detail.SellDetailCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_detail.SellDetailShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_detail.SellDetailUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellDetail;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/sell_details")
public class SellDetailController extends
        BaseControllerImplementation<SellDetail, SellDetailCreateDTO, SellDetailShowDTO, SellDetailUpdateDTO, SellDetailActivatedDTO> {

}
