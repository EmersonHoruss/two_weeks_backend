package com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.type;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Type;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class TypeUpdateDTO extends BaseUpdateDTO<Type> {
    @NotBlank
    private String name;

    @Override
    public Type asEntity() {
        Type type = new Type();
        type.setId(this.getId());
        type.setName(this.getName().trim());
        return type;
    }
}
