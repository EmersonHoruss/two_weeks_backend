package com.two_weeks_backend.two_weeks_backend.controllers.sell;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_change_detail.SellChangeDetailActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_change_detail.SellChangeDetailCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_change_detail.SellChangeDetailShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_change_detail.SellChangeDetailUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellChangeDetail;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/sell_change_details")
public class SellChangeDetailController extends
        BaseControllerImplementation<SellChangeDetail, SellChangeDetailCreateDTO, SellChangeDetailShowDTO, SellChangeDetailUpdateDTO, SellChangeDetailActivatedDTO> {

}
