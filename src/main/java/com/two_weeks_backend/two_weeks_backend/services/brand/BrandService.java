package com.two_weeks_backend.two_weeks_backend.services.brand;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.entities.brand.Brand;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;

@Service
public class BrandService extends BaseServiceImplementation<Brand> {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Brand update(Brand brand) {
        Brand retrievedBrand = this.baseRepository.getReferenceById(brand.getId());

        if (brand.getName().trim().equals(retrievedBrand.getName()))
            return retrievedBrand;

        retrievedBrand.setName(brand.getName());

        return this.baseRepository.save(retrievedBrand);
    }

    @Transactional(rollbackFor = Exception.class)
    public Brand setActivation(Brand brand) {
        Brand retrievedBrand = this.baseRepository.getReferenceById(brand.getId());

        if (brand.getActivated() == retrievedBrand.getActivated())
            return retrievedBrand;

        retrievedBrand.setActivated(brand.getActivated());

        return this.baseRepository.save(retrievedBrand);
    }
}
