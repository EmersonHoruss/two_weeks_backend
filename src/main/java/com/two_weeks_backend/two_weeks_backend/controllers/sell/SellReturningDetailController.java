package com.two_weeks_backend.two_weeks_backend.controllers.sell;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_returning_detail.SellReturningDetailActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_returning_detail.SellReturningDetailCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_returning_detail.SellReturningDetailShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_returning_detail.SellReturningDetailUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellReturningDetail;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/sell_returning_details")
public class SellReturningDetailController extends
        BaseControllerImplementation<SellReturningDetail, SellReturningDetailCreateDTO, SellReturningDetailShowDTO, SellReturningDetailUpdateDTO, SellReturningDetailActivatedDTO> {

}
