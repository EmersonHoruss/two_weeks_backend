package com.two_weeks_backend.two_weeks_backend.DTOs.entities.size;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.size.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SizeShowDTO extends BaseShowDTO<Size> {
    private String name;
    private boolean activated;
}
