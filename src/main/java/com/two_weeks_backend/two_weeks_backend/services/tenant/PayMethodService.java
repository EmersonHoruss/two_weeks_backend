package com.two_weeks_backend.two_weeks_backend.services.tenant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.two_weeks_backend.two_weeks_backend.entities.tenant.PayMethod;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;
import com.two_weeks_backend.two_weeks_backend.utils.specification.Specification;

@Service
public class PayMethodService extends BaseServiceImplementation<PayMethod> {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public PayMethod create(PayMethod payMethod) {
        String nameInTenant = payMethod.getNameInTenant();

        boolean existsPayMethodInTenant = this.existsPayMethodInTenant(nameInTenant);
        if (existsPayMethodInTenant)
            throw new RuntimeException("Ya existe el método de pago " + payMethod.getName());

        return this.baseRepository.save(payMethod);
    }

    private boolean existsPayMethodInTenant(String nameInTenant) {
        String attribute = "nameInTenant";
        String operator = "<eq>";
        String query = attribute + operator + nameInTenant;
        Specification<PayMethod> specification = new Specification<>(query);

        Pageable pageable = PageRequest.of(0, 1);

        Page<PayMethod> page = super.get(specification, pageable);

        return !page.isEmpty();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PayMethod update(PayMethod payMethod) {
        PayMethod retrievedPayMethod = this.baseRepository.getReferenceById(payMethod.getId());

        if (retrievedPayMethod == null)
            throw new RuntimeException("No se ha encontrado el método de pago");

        String nameInTenant = payMethod.getName() + retrievedPayMethod.getTenant().getId();
        boolean existsTypeInTenant = this.existsPayMethodInTenant(nameInTenant);
        if (existsTypeInTenant && !payMethod.getName().equals(retrievedPayMethod.getName()))
            throw new RuntimeException("Ya existe el método de pago " + payMethod.getName());

        if (!retrievedPayMethod.getActivated())
            throw new RuntimeException("El método de pago " + retrievedPayMethod.getName() + " está eliminado");

        if (payMethod.getName().equals(retrievedPayMethod.getName())
                && payMethod.getIsVirtual() == retrievedPayMethod.getIsVirtual())
            return retrievedPayMethod;

        retrievedPayMethod.setName(payMethod.getName());
        retrievedPayMethod.setIsVirtual(payMethod.getIsVirtual());
        return this.baseRepository.save(retrievedPayMethod);
    }

    @Transactional(rollbackFor = Exception.class)
    public PayMethod setActivation(PayMethod payMethod) {
        PayMethod retrievedPayMethod = this.baseRepository.getReferenceById(payMethod.getId());

        if (retrievedPayMethod == null)
            throw new RuntimeException("No se ha encontrado el método de pago");

        if (payMethod.getActivated() == retrievedPayMethod.getActivated())
            return retrievedPayMethod;

        retrievedPayMethod.setActivated(payMethod.getActivated());
        return this.baseRepository.save(retrievedPayMethod);
    }
}
