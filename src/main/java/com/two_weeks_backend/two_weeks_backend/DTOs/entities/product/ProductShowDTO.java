package com.two_weeks_backend.two_weeks_backend.DTOs.entities.product;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ProductShowDTO extends BaseShowDTO<Product>{
    private String type;
    private String brand;
    private String size;
    private short stock;
    private float purchasePrice;
    private float sellPriceNormal;
    private float sellPriceWholesale1;
    private float sellPriceWholesale2;
    private String name;
    private String code;
}
