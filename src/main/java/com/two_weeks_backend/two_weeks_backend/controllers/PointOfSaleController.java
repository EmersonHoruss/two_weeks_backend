package com.two_weeks_backend.two_weeks_backend.controllers;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.pointOfSale.PointOfSaleActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.pointOfSale.PointOfSaleCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.pointOfSale.PointOfSaleShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.pointOfSale.PointOfSaleUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.PointOfSale;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/point_of_sales")
public class PointOfSaleController extends BaseControllerImplementation<
    PointOfSale,
    PointOfSaleCreateDTO,
    PointOfSaleShowDTO,
    PointOfSaleUpdateDTO,
    PointOfSaleActivatedDTO
>{
}
