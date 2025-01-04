package com.two_weeks_backend.two_weeks_backend.DTOs.entities.type;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.type.Type;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeUpdateDTO extends BaseUpdateDTO<Type> {
    @NotBlank
    private String name;

    @Override
    public Type asEntity() {
        Type type = new Type();
        type.setId(this.getId());
        type.setName(this.getName());
        return type;
    }
}
