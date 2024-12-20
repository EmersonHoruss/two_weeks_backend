package com.two_weeks_backend.two_weeks_backend.DTOs.entities.product.type;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.product.Type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeShowDTO extends BaseShowDTO<Type> {
    private String name;
    private boolean activated;
}
