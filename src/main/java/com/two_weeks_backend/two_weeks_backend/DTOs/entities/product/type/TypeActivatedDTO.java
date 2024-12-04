package com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.type;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.product.Type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeActivatedDTO extends BaseActivatedDTO<Type> {
    @Override
    public Type asEntity() {
        Type type = new Type();
        type.setId(this.getId());
        type.setActivated(this.getActivated());
        return type;
    }
}
