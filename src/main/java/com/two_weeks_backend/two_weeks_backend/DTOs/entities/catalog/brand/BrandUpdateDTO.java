package com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.brand;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Brand;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class BrandUpdateDTO extends BaseUpdateDTO<Brand> {
    @NotBlank
    private String name;

    @Override
    public Brand asEntity() {
        Brand brand = new Brand();
        brand.setId(this.getId());
        brand.setName(this.getName().trim());
        return brand;
    }
}
