package com.two_weeks_backend.two_weeks_backend.services.tenant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;
import com.two_weeks_backend.two_weeks_backend.utils.specification.Specification;

@Service
public class TenantService extends BaseServiceImplementation<Tenant> {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tenant create(Tenant tenant) {
        String tenantName = tenant.getName();
        boolean existsTenant = this.existsTenant(tenantName);

        if (existsTenant)
            throw new RuntimeException("Ya existe un cliente con el nombre " + tenantName);

        return this.baseRepository.save(tenant);
    }

    private boolean existsTenant(String name) {
        String attribute = "name";
        String operator = "<eq>";
        String query = attribute + operator + name;
        Specification<Tenant> specification = new Specification<>(query);

        Pageable pageable = PageRequest.of(0, 1);

        Page<Tenant> page = super.get(specification, pageable);

        return !page.isEmpty();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tenant update(Tenant tenant) {
        String tenantName = tenant.getName();
        boolean existsTenant = this.existsTenant(tenantName);

        if (existsTenant)
            throw new RuntimeException("Ya existe un cliente con el nombre " + tenantName);

        Tenant retrievedTenant = this.baseRepository.getReferenceById(tenant.getId());

        if (!retrievedTenant.getActivated())
            throw new RuntimeException("El cliente " + tenantName + " está eliminado");

        if (tenantName.equals(retrievedTenant.getName()))
            return retrievedTenant;

        retrievedTenant.setName(tenant.getName());
        return this.baseRepository.save(retrievedTenant);
    }

    @Transactional(rollbackFor = Exception.class)
    public Tenant setActivation(Tenant tenant) {
        Tenant retrievedTenant = this.baseRepository.getReferenceById(tenant.getId());

        if (tenant.getActivated() == retrievedTenant.getActivated())
            return retrievedTenant;

        retrievedTenant.setActivated(tenant.getActivated());
        return this.baseRepository.save(retrievedTenant);
    }
}
