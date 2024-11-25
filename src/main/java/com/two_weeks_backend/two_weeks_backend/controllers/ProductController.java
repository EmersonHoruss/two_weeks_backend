package com.two_weeks_backend.two_weeks_backend.controllers;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.ProductActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.ProductCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.ProductShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.ProductUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.Product;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/products")
public class ProductController extends BaseControllerImplementation<
    Product,
    ProductCreateDTO,
    ProductShowDTO,
    ProductUpdateDTO,
    ProductActivatedDTO
>{
}
