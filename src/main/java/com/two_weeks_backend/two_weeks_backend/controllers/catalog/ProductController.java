package com.two_weeks_backend.two_weeks_backend.controllers.catalog;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.product.ProductActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.product.ProductCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.product.ProductShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.product.ProductUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/products")
public class ProductController extends
        BaseControllerImplementation<Product, ProductCreateDTO, ProductShowDTO, ProductUpdateDTO, ProductActivatedDTO> {

}
