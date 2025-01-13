package com.two_weeks_backend.two_weeks_backend.services.tenant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;
import com.two_weeks_backend.two_weeks_backend.utils.BarCodeGenerator;
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

        String countryCode = tenant.getCountryCode();
        String companyCode = tenant.getCompanyCode();
        String barCode = BarCodeGenerator.firstCodeBar(countryCode, companyCode);
        tenant.setLastConsecutiveBarCode(barCode);

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
        Tenant retrievedTenant = this.baseRepository.getReferenceById(tenant.getId());

        if (retrievedTenant == null)
            throw new RuntimeException("No se ha encontrado el cliente");

        String nameInTenant = tenant.getName();
        boolean existsTenant = this.existsTenant(nameInTenant);
        if (existsTenant && !nameInTenant.equals(retrievedTenant.getName()))
            throw new RuntimeException("Ya existe un cliente con el nombre " + nameInTenant);

        if (!retrievedTenant.getActivated())
            throw new RuntimeException("El cliente " + retrievedTenant.getName() + " está eliminado");

        if (nameInTenant.equals(retrievedTenant.getName())
                && tenant.getIsFlexModeActivated() == retrievedTenant.getIsFlexModeActivated()
                && tenant.getCompanyCode().equals(retrievedTenant.getCompanyCode())
                && tenant.getCountryCode().equals(retrievedTenant.getCountryCode())
                && tenant.getLastConsecutiveBarCode().equals(retrievedTenant.getLastConsecutiveBarCode()))
            return retrievedTenant;

        retrievedTenant.setName(tenant.getName());
        retrievedTenant.setIsFlexModeActivated(tenant.getIsFlexModeActivated());
        retrievedTenant.setCompanyCode(tenant.getCompanyCode());
        retrievedTenant.setCountryCode(tenant.getCountryCode());
        retrievedTenant.setLastConsecutiveBarCode(tenant.getLastConsecutiveBarCode());
        return this.baseRepository.save(retrievedTenant);
    }

    @Transactional(rollbackFor = Exception.class)
    public Tenant setActivation(Tenant tenant) {
        Tenant retrievedTenant = this.baseRepository.getReferenceById(tenant.getId());

        if (retrievedTenant == null)
            throw new RuntimeException("No se ha encontrado el cliente");

        if (tenant.getActivated() == retrievedTenant.getActivated())
            return retrievedTenant;

        retrievedTenant.setActivated(tenant.getActivated());
        return this.baseRepository.save(retrievedTenant);
    }

    public void createNextBarCode(Tenant tenant) {
        if (!tenant.getActivated())
            throw new RuntimeException("El cliente " + tenant.getName() + " está eliminado");

        String countryCode = tenant.getCountryCode();
        String companyCode = tenant.getCompanyCode();
        String barCode = tenant.getLastConsecutiveBarCode();
        String nextBarCode = BarCodeGenerator.getNextCodeBar(countryCode, companyCode, barCode);

        tenant.setLastConsecutiveBarCode(nextBarCode);
        this.baseRepository.save(tenant);
    }

    public Tenant validate(Long tenantId) {
        if (tenantId == null)
            throw new RuntimeException("Identificador del cliente no existe");

        Tenant tenant = this.baseRepository.getReferenceById(tenantId);

        if (tenant == null)
            throw new RuntimeException("No existe el cliente con el identificador " + tenantId);

        if (!tenant.getActivated())
            throw new RuntimeException("El cliente " + tenant.getName() + " está eliminado");

        return tenant;
    }
}
