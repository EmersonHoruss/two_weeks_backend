package com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.brand;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Brand;

import lombok.*;

@Getter
@Setter
public class BrandShowDTO extends BaseShowDTO<Brand> {
    private String name;
    private boolean activated;
}
