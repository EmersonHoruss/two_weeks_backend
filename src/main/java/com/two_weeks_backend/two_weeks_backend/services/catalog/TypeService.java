package com.two_weeks_backend.two_weeks_backend.services.catalog;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.two_weeks_backend.two_weeks_backend.entities.catalog.Type;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;
import com.two_weeks_backend.two_weeks_backend.utils.specification.Specification;

@Service
public class TypeService extends BaseServiceImplementation<Type> {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Type create(Type type) {
        String nameInTenant = type.getNameInTenant();

        boolean existsTypeInTenant = this.existsTypeInTenant(nameInTenant);
        if (existsTypeInTenant)
            throw new RuntimeException("Ya existe el tipo " + type.getName());

        return this.baseRepository.save(type);
    }

    private boolean existsTypeInTenant(String nameInTenant) {
        String attribute = "nameInTenant";
        String operator = "<eq>";
        String query = attribute + operator + nameInTenant;
        Specification<Type> specification = new Specification<>(query);

        Pageable pageable = PageRequest.of(0, 1);

        Page<Type> page = super.get(specification, pageable);

        return !page.isEmpty();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Type update(Type type) {
        Type retrievedType = this.baseRepository.getReferenceById(type.getId());

        if (retrievedType == null)
            throw new RuntimeException("No se ha encontrado el tipo");

        String nameInTenant = type.getName() + retrievedType.getTenant().getId();
        boolean existsTypeInTenant = this.existsTypeInTenant(nameInTenant);
        if (existsTypeInTenant)
            throw new RuntimeException("Ya existe el tipo " + type.getName());

        if (!retrievedType.getActivated())
            throw new RuntimeException("El tipo " + type.getName() + " está eliminado");

        if (type.getName().equals(retrievedType.getName()))
            return retrievedType;

        retrievedType.setName(type.getName());
        return this.baseRepository.save(retrievedType);
    }

    @Transactional(rollbackFor = Exception.class)
    public Type setActivation(Type type) {
        Type retrievedType = this.baseRepository.getReferenceById(type.getId());

        if (retrievedType == null)
            throw new RuntimeException("No se ha encontrado el tipo");

        if (type.getActivated() == retrievedType.getActivated())
            return retrievedType;

        retrievedType.setActivated(type.getActivated());
        return this.baseRepository.save(retrievedType);
    }
}
