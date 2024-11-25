package com.two_weeks_backend.two_weeks_backend.controllers;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.detailSell.DetailSellActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.detailSell.DetailSellCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.detailSell.DetailSellShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.detailSell.DetailSellUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.DetailSell;
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
