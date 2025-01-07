package com.two_weeks_backend.two_weeks_backend.DTOs.entities.catalog.type;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.catalog.Type;

import lombok.*;

@Getter
@Setter
public class TypeShowDTO extends BaseShowDTO<Type> {
    private String name;
    private boolean activated;
}
