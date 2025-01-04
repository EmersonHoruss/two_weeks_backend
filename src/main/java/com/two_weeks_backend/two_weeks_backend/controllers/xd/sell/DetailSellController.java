package com.two_weeks_backend.two_weeks_backend.controllers.sell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.detailSell.DetailSellActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.detailSell.DetailSellCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.detailSell.DetailSellShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.detailSell.DetailSellUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.sell.DetailSell;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/detail_sells")
public class DetailSellController extends BaseControllerImplementation<
    DetailSell,
    DetailSellCreateDTO,
    DetailSellShowDTO,
    DetailSellUpdateDTO,
    DetailSellActivatedDTO
>{
}
