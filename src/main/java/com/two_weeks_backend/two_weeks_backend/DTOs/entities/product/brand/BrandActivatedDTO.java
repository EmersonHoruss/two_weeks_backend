package com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.brand;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.product.Brand;

import lombok.Getter;
import lombok.Setter;

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
