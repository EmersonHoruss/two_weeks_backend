package com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.brand;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.product.Brand;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandShowDTO extends BaseShowDTO<Brand> {
    private String name;
    private boolean activated;
}
