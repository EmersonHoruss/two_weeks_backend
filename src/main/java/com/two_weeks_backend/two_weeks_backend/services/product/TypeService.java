package com.two_weeks_backend.two_weeks_backend.services.product;

import org.springframework.stereotype.Service;

import com.two_weeks_backend.two_weeks_backend.entities.product.Type;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;

@Service
public class TypeService extends BaseServiceImplementation<Type> {
    @Override
    public Type create(Type type) {
        Type savedType = super.create(type);
        return savedType;
    }

    @Override
    public Type update(Type type) {
        Type retrievedType = baseRepository.getReferenceById(type.getId());
        type.setActivated(retrievedType.getActivated());
        return baseRepository.save(type);
    }

    public Type setActivation(Type type) {
        Type retrievedType = baseRepository.getReferenceById(type.getId());
        retrievedType.setActivated(type.getActivated());
        return baseRepository.save(retrievedType);
    }
}
