package com.two_weeks_backend.two_weeks_backend.controllers.point_of_sale;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.point_of_sale.point_of_sale.PointOfSaleActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.point_of_sale.point_of_sale.PointOfSaleCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.point_of_sale.point_of_sale.PointOfSaleShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.point_of_sale.point_of_sale.PointOfSaleUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.point_of_sale.PointOfSale;

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
