package com.two_weeks_backend.two_weeks_backend.services.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.entities.management.EntityName;
import com.two_weeks_backend.two_weeks_backend.entities.management.ManagementCode;
import com.two_weeks_backend.two_weeks_backend.entities.product.Type;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;
import com.two_weeks_backend.two_weeks_backend.services.management.ManagementCodeService;

@Service
public class TypeService extends BaseServiceImplementation<Type> {
    @Autowired
    private ManagementCodeService managementCodeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Type create(Type type) {
        ManagementCode managementCode = this.managementCodeService.get(EntityName.TYPE);
        String code = "";
        if (managementCode == null) {
            managementCode = new ManagementCode(EntityName.TYPE);
            code = managementCode.getFirstCode();
            managementCode.subtractCode(code);
            this.managementCodeService.create(managementCode);
        }

        if (managementCode.allCodesAreUsed()) {
            throw new RuntimeException("se ha alcanzado el límite de códigos, no se puede crear ningún tipo más");
        }

        type.setCode(code);
        Type savedType = super.create(type);
        return savedType;
    }

    @Override
    public Type update(Type type) {
        Type retrievedType = baseRepository.getReferenceById(type.getId());
        type.setActivated(retrievedType.getActivated());
        type.setCode(retrievedType.getCode());
        return baseRepository.save(type);
    }

    public Type setActivation(Type type) {
        Type retrievedType = baseRepository.getReferenceById(type.getId());
        retrievedType.setActivated(type.getActivated());
        return baseRepository.save(retrievedType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {

        Type type = baseRepository.getReferenceById(id);
        this.baseRepository.delete(type);

        ManagementCode managementCode = this.managementCodeService.get(EntityName.TYPE);
        if (managementCode == null)
            throw new RuntimeException("eliminación no se puede ejecutar porque no existe el código");

        String code = type.getCode();
        managementCode.addCode(code);
        this.managementCodeService.update(managementCode);
    }
}
