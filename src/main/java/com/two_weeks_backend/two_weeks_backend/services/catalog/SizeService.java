package com.two_weeks_backend.two_weeks_backend.services.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.entities.catalog.Size;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;
import com.two_weeks_backend.two_weeks_backend.utils.specification.Specification;

@Service
public class SizeService extends BaseServiceImplementation<Size> {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Size create(Size size) {
        String nameInTenant = size.getNameInTenant();

        boolean existsSizeInTenant = this.existsSizeInTenant(nameInTenant);
        if (existsSizeInTenant)
            throw new RuntimeException("Ya existe la talla " + size.getName());

        return this.baseRepository.save(size);
    }

    private boolean existsSizeInTenant(String nameInTenant) {
        String attribute = "nameInTenant";
        String operator = "<eq>";
        String query = attribute + operator + nameInTenant;
        Specification<Size> specification = new Specification<>(query);

        Pageable pageable = PageRequest.of(0, 1);

        Page<Size> page = super.get(specification, pageable);

        return !page.isEmpty();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Size update(Size size) {
        Size retrievedSize = this.baseRepository.getReferenceById(size.getId());

        if (retrievedSize == null)
            throw new RuntimeException("No se ha encontrado la talla");

        String nameInTenant = size.getName() + retrievedSize.getTenant().getId();
        boolean existsSizeInTenant = this.existsSizeInTenant(nameInTenant);
        if (existsSizeInTenant)
            throw new RuntimeException("Ya existe la talla " + size.getName());

        if (!retrievedSize.getActivated())
            throw new RuntimeException("La talla " + size.getName() + " está eliminada");

        if (size.getName().equals(retrievedSize.getName()))
            return retrievedSize;

        return this.baseRepository.save(retrievedSize);
    }

    @Transactional(rollbackFor = Exception.class)
    public Size setActivation(Size size) {
        Size retrievedSize = this.baseRepository.getReferenceById(size.getId());

        if (retrievedSize == null)
            throw new RuntimeException("No se ha encontrado la talla");

        if (size.getActivated() == retrievedSize.getActivated())
            return retrievedSize;

        retrievedSize.setActivated(size.getActivated());
        return this.baseRepository.save(retrievedSize);
    }
}
