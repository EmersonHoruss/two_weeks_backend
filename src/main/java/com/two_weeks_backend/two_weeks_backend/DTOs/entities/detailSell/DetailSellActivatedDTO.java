package com.two_weeks_backend.two_weeks_backend.DTOs.entities.detailSell;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.entities.DetailSell;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailSellActivatedDTO extends BaseActivatedDTO<DetailSell> {
    @Override
    public DetailSell asEntity() {
        DetailSell detailSell = new DetailSell();
        detailSell.setId(this.getId());
        detailSell.setActivated(this.getActivated());
        return detailSell;
    }
}
