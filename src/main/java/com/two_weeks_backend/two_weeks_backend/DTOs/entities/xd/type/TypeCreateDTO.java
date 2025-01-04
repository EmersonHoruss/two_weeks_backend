package com.two_weeks_backend.two_weeks_backend.DTOs.entities.type;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.type.Type;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeCreateDTO extends BaseCreateDTO<Type> {
    @NotBlank
    private String name;

    @Override
    public Type asEntity() {
        Type type = new Type();
        type.setName(this.getName());
        return type;
    }
}
