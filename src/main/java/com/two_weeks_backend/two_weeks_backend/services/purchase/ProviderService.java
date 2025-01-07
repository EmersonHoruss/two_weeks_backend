package com.two_weeks_backend.two_weeks_backend.services.purchase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.two_weeks_backend.two_weeks_backend.entities.purchase.Provider;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;
import com.two_weeks_backend.two_weeks_backend.utils.specification.Specification;

@Service
public class ProviderService extends BaseServiceImplementation<Provider> {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Provider create(Provider provider) {
        String aliasInTenant = provider.getAliasInTenant();

        boolean existsProviderInTenant = this.existsProviderInTenant(aliasInTenant);
        if (existsProviderInTenant)
            throw new RuntimeException("Ya existe el proveedor con el alias " + provider.getAlias());

        return this.baseRepository.save(provider);
    }

    private boolean existsProviderInTenant(String aliasInTenant) {
        String attribute = "aliasInTenant";
        String operator = "<eq>";
        String query = attribute + operator + aliasInTenant;
        Specification<Provider> specification = new Specification<>(query);

        Pageable pageable = PageRequest.of(0, 1);

        Page<Provider> page = super.get(specification, pageable);

        return !page.isEmpty();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Provider update(Provider provider) {
        Provider retrievedProvider = this.baseRepository.getReferenceById(provider.getId());

        if (retrievedProvider == null)
            throw new RuntimeException("No se ha encontrado el proveedor");

        String aliasInTenant = provider.getAlias() + retrievedProvider.getTenant().getId();
        boolean existsProviderInTenant = this.existsProviderInTenant(aliasInTenant);
        if (existsProviderInTenant && !provider.getAlias().equals(retrievedProvider.getAlias()))
            throw new RuntimeException("Ya existe el proveedor con el alias " + provider.getAlias());

        if (!retrievedProvider.getActivated())
            throw new RuntimeException("El proveedor " + provider.getName() + " está eliminado");

        retrievedProvider.setName(provider.getName());
        retrievedProvider.setAlias(provider.getAlias());
        retrievedProvider.setPhone(provider.getPhone());
        retrievedProvider.setWhatsapp(provider.getWhatsapp());
        retrievedProvider.setBcpAccount(provider.getBcpAccount());
        retrievedProvider.setBcpAccountCCI(provider.getBcpAccountCCI());
        retrievedProvider.setBbvaAccount(provider.getBbvaAccount());
        retrievedProvider.setBbvaAccountCCI(provider.getBbvaAccountCCI());
        retrievedProvider.setYape(provider.getYape());
        retrievedProvider.setPlin(provider.getPlin());
        return this.baseRepository.save(retrievedProvider);
    }

    @Transactional(rollbackFor = Exception.class)
    public Provider setActivation(Provider provider) {
        Provider retrievedProvider = this.baseRepository.getReferenceById(provider.getId());

        if (provider.getActivated() == retrievedProvider.getActivated())
            return retrievedProvider;

        retrievedProvider.setActivated(provider.getActivated());
        return this.baseRepository.save(retrievedProvider);
    }
}
