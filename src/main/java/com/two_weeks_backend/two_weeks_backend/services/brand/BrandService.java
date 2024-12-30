package com.two_weeks_backend.two_weeks_backend.services.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.entities.brand.Brand;
import com.two_weeks_backend.two_weeks_backend.entities.management.EntityName;
import com.two_weeks_backend.two_weeks_backend.entities.management.ManagementCode;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;
import com.two_weeks_backend.two_weeks_backend.services.management.ManagementCodeService;

@Service
public class BrandService extends BaseServiceImplementation<Brand> {
    @Autowired
    private ManagementCodeService managementCodeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Brand create(Brand brand) {
        ManagementCode managementCode = this.managementCodeService.get(EntityName.BRAND);
        String code = "";
        if (managementCode == null) {
            managementCode = new ManagementCode(EntityName.BRAND);
            code = managementCode.getFirstCode();
            managementCode.subtractCode(code);
            this.managementCodeService.create(managementCode);
        } else {
            code = managementCode.getFirstCode();
            managementCode.subtractCode(code);
            this.managementCodeService.update(managementCode);
        }

        if (managementCode.allCodesAreUsed()) {
            throw new RuntimeException("se ha alcanzado el límite de códigos, no se puede crear ninguna marca más");
        }

        brand.setCode(code);
        Brand savedType = super.create(brand);
        return savedType;
    }

    @Override
    public Brand update(Brand brand) {
        Brand retrievedBrand = this.baseRepository.getReferenceById(brand.getId());
        brand.setActivated(retrievedBrand.getActivated());
        brand.setCode(retrievedBrand.getCode());
        return this.baseRepository.save(brand);
    }

    public Brand setActivation(Brand brand) {
        Brand retrievedBrand = this.baseRepository.getReferenceById(brand.getId());
        retrievedBrand.setActivated(brand.getActivated());
        return this.baseRepository.save(retrievedBrand);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {

        Brand brand = this.baseRepository.getReferenceById(id);
        this.baseRepository.delete(brand);

        ManagementCode managementCode = this.managementCodeService.get(EntityName.TYPE);
        if (managementCode == null)
            throw new RuntimeException("eliminación no se puede ejecutar porque no existe el código");

        String code = brand.getCode();
        managementCode.addCode(code);
        this.managementCodeService.update(managementCode);
    }
}
