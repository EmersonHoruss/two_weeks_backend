package com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.brand;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.product.Brand;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandCreateDTO extends BaseCreateDTO<Brand> {
    @NotBlank
    private String name;

    @Override
    public Brand asEntity() {
        Brand brand = new Brand();
        brand.setName(this.getName());
        return brand;
    }
}
