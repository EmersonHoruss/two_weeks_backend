package com.two_weeks_backend.two_weeks_backend.services.sell;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell.SellCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_detail.SellDetailCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_pay_method.SellPayMethodCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Sell;
import com.two_weeks_backend.two_weeks_backend.repositories.sell.SellRepository;

public class SellService {
    @Autowired
    private SellRepository sellRepository;

    @Autowired
    private SellDetailService sellDetailService;

    @Autowired
    private SellPayMethodService sellPayMethodService;

    @Transactional(rollbackFor = Exception.class)
    public Sell create(SellCreateDTO sellCreateDTO) {
        List<SellDetailCreateDTO> details = sellCreateDTO.getDetails();
        List<SellPayMethodCreateDTO> payMethods = sellCreateDTO.getPayMethods();

        
        Totals payMethodTotals = this.sellPayMethodService.getTotals(payMethods);
        sell.setTotal(totals.getTotal());
        sell.setTotalVirtual(totals.getTotalVirtual());
        sell.setTotalPhisical(totals.getTotalPhisical());
        float total = details.// iterate and get the sum of all subTotal
        this.validate(sellCreateDTO, payMethodTotals, total);

        Sell sell = sellCreateDTO.asEntity();

        this.sellDetailService.create(details);

        this.sellPayMethodService.create(payMethods);

        
        return this.sellRepository.save(sell);
    }

    private void validate(SellCreateDTO sellCreateDTO) {
        //subtotal matches with the paymethod

        //seller active and exists

        //tenant exists

        //company active and exists
    }
}
