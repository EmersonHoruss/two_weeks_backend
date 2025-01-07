package com.two_weeks_backend.two_weeks_backend.services.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.entities.catalog.Brand;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;
import com.two_weeks_backend.two_weeks_backend.utils.specification.Specification;

@Service
public class BrandService extends BaseServiceImplementation<Brand> {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Brand create(Brand brand) {
        String nameInTenant = brand.getNameInTenant();

        boolean existsBrandInTenant = this.existsBrandInTenant(nameInTenant);
        if (existsBrandInTenant)
            throw new RuntimeException("Ya existe la marca " + brand.getName());

        return this.baseRepository.save(brand);
    }

    private boolean existsBrandInTenant(String nameInTenant) {
        String attribute = "nameInTenant";
        String operator = "<eq>";
        String query = attribute + operator + nameInTenant;
        Specification<Brand> specification = new Specification<>(query);

        Pageable pageable = PageRequest.of(0, 1);

        Page<Brand> page = super.get(specification, pageable);

        return !page.isEmpty();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Brand update(Brand brand) {
        Brand retrievedBrand = this.baseRepository.getReferenceById(brand.getId());

        if (retrievedBrand == null)
            throw new RuntimeException("No se ha encontrado la marca");

        String nameInTenant = brand.getName() + retrievedBrand.getTenant().getId();
        boolean existBrandInTenant = this.existsBrandInTenant(nameInTenant);
        if (existBrandInTenant)
            throw new RuntimeException("Ya existe la marca " + brand.getName());

        if (!retrievedBrand.getActivated())
            throw new RuntimeException("La marca " + brand.getName() + " está eliminada");

        if (brand.getName().equals(retrievedBrand.getName()))
            return retrievedBrand;

        retrievedBrand.setName(brand.getName());
        return this.baseRepository.save(retrievedBrand);
    }

    @Transactional(rollbackFor = Exception.class)
    public Brand setActivation(Brand brand) {
        Brand retrievedBrand = this.baseRepository.getReferenceById(brand.getId());

        if (retrievedBrand == null)
            throw new RuntimeException("No se ha encontrado la marca");

        if (brand.getActivated() == retrievedBrand.getActivated())
            return retrievedBrand;

        retrievedBrand.setActivated(brand.getActivated());
        return this.baseRepository.save(retrievedBrand);
    }
}
