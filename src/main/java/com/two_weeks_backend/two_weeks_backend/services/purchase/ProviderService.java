package com.two_weeks_backend.two_weeks_backend.services.purchase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.entities.purchase.Provider;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;

@Service
public class ProviderService extends BaseServiceImplementation<Provider> {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Provider update(Provider provider) {
        Provider retrievedProvider = this.baseRepository.getReferenceById(provider.getId());

        if (retrievedProvider == null)
            throw new RuntimeException("No se ha encontrado el proveedor");

        if (!retrievedProvider.getActivated())
            throw new RuntimeException("El proveedor " + provider.getName() + " está eliminado");

        retrievedProvider.setName(provider.getName());
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
