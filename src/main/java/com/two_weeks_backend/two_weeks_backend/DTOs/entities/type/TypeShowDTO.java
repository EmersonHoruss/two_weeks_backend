package com.two_weeks_backend.two_weeks_backend.DTOs.entities.type;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.type.Type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeShowDTO extends BaseShowDTO<Type> {
    private String name;
    private String code;
    private boolean activated;
}
