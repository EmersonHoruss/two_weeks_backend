package com.two_weeks_backend.two_weeks_backend.DTOs.entities.brand;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.brand.Brand;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandShowDTO extends BaseShowDTO<Brand> {
    private String name;
    private String code;
    private boolean activated;
}
