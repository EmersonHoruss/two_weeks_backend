package com.two_weeks_backend.two_weeks_backend.DTOs.entities.compra;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.CompraEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompraActivatedDTO extends BaseActivatedDTO<CompraEntity> {

    @Override
    public CompraEntity asEntity() {
        CompraEntity compra = new CompraEntity();
        compra.setId(this.getId());
        compra.setActivated(this.getActivated());
        return compra;
    }

}
