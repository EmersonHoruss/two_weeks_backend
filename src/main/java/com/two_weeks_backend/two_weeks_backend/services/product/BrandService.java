package com.two_weeks_backend.two_weeks_backend.services.product;

import org.springframework.stereotype.Service;

import com.two_weeks_backend.two_weeks_backend.entities.product.Brand;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;

@Service
public class BrandService extends BaseServiceImplementation<Brand> {
    @Override
    public Brand create(Brand brand) {
        Brand savedBrand = super.create(brand);
        return savedBrand;
    }
}
