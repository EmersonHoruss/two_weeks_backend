package com.two_weeks_backend.two_weeks_backend.DTOs.entities.distribuidor;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.DistribuidorEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistribuidorActivatedDTO extends BaseActivatedDTO<DistribuidorEntity> {
    @Override
    public DistribuidorEntity asEntity() {
        DistribuidorEntity distribuidorEntity = new DistribuidorEntity();
        distribuidorEntity.setId(this.getId());
        distribuidorEntity.setActivated(this.getActivated());
        return distribuidorEntity;
    }
}
