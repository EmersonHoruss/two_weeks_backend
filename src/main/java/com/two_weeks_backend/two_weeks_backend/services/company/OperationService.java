package com.two_weeks_backend.two_weeks_backend.services.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.entities.company.Operation;
import com.two_weeks_backend.two_weeks_backend.entities.company.OperationType;
import com.two_weeks_backend.two_weeks_backend.entities.company.ProductCompany;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;
import com.two_weeks_backend.two_weeks_backend.services.tenant.TenantService;

@Service
public class OperationService extends BaseServiceImplementation<Operation> {
    @Autowired
    private ProductCompanyService productCompanyService;

    @Autowired
    private TenantService tenantService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Operation create(Operation operation) {
        Long productId = operation.getProduct().getId();
        Long companyId = operation.getCompany().getId();
        ProductCompany retrievedProductCompany = this.productCompanyService.getProductCompany(productId, companyId);

        if (retrievedProductCompany == null)
            throw new RuntimeException("Producto de la empresa no encontrado");

        if (!retrievedProductCompany.getActivated() || !retrievedProductCompany.getProduct().getActivated())
            throw new RuntimeException("El producto de la empresa o del cliente está eliminado");

        this.validateTenant(retrievedProductCompany.getProduct().getTenant().getId());

        Long productCompanyId = retrievedProductCompany.getId();
        int amount = operation.getAmount();
        if (operation.getType() == OperationType.ADD)
            this.productCompanyService.addStock(productCompanyId, amount);
        else
            this.productCompanyService.subtractStock(productCompanyId, amount);

        return this.baseRepository.save(operation);
    }

    private void validateTenant(Long tenantId) {
        Tenant tenant = this.tenantService.get(tenantId);
        if (tenant == null)
            throw new RuntimeException("No existe el cliente");

        if (!tenant.getIsFlexModeActivated())
            throw new RuntimeException(
                    "El cliente debe tener habilitada el modo flexible para poder realizar operaciones, solicítelo al dueño o director de TI de más alto rango de toda la organización");
    }

    @Override
    public Operation update(Operation operation) {
        throw new UnsupportedOperationException("Operación no soporta actualización");
    }

    public Operation setActivation(Operation operation) {
        throw new UnsupportedOperationException("Operación no soporta eliminación");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Operación no soporta eliminación permanente");
    }
}
