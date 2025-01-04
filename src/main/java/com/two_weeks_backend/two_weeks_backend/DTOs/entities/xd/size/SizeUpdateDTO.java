package com.two_weeks_backend.two_weeks_backend.DTOs.entities.size;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.size.Size;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SizeUpdateDTO extends BaseUpdateDTO<Size> {
    @NotBlank
    private String name;

    @Override
    public Size asEntity() {
        Size size = new Size();
        size.setId(this.getId());
        size.setName(this.getName());
        return size;
    }
}
