package com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.brand;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Brand;

import lombok.*;

@Getter
@Setter
public class BrandActivatedDTO extends BaseActivatedDTO<Brand> {
    @Override
    public Brand asEntity() {
        Brand brand = new Brand();
        brand.setId(this.getId());
        brand.setActivated(this.getActivated());
        return brand;
    }
}
