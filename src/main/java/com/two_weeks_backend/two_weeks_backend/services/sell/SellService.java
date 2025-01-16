package com.two_weeks_backend.two_weeks_backend.services.sell;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell.SellCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell.SellUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_detail.SellDetailCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_pay_method.SellPayMethodCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Sell;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellPayMethod;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Totals;
import com.two_weeks_backend.two_weeks_backend.repositories.sell.SellRepository;
import com.two_weeks_backend.two_weeks_backend.services.company.CompanyService;
import com.two_weeks_backend.two_weeks_backend.services.tenant.TenantService;
import com.two_weeks_backend.two_weeks_backend.services.user.UserService;

public class SellService {
    @Autowired
    private SellRepository sellRepository;

    @Autowired
    private SellDetailService sellDetailService;

    @Autowired
    private SellPayMethodService sellPayMethodService;

    @Autowired
    private UserService userService;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private CompanyService companyService;

    @Transactional(rollbackFor = Exception.class)
    public Sell create(SellCreateDTO sellCreateDTO) {
        Sell sell = sellCreateDTO.asEntity();

        this.validate(sellCreateDTO);

        sell = this.sellRepository.save(sell);
        Long sellId = sell.getId();
        Long companyId = sellCreateDTO.getCompanyId();

        List<SellPayMethodCreateDTO> sellPayMethodsDTO = sellCreateDTO.getPayMethods();
        List<SellPayMethod> sellPayMethods = this.sellPayMethodService.create(sellPayMethodsDTO, sellId);
        Totals totals = this.sellPayMethodService.getTotals(sellPayMethods);
        sell.setTotal(totals.getTotal());
        sell.setTotalPhisical(totals.getTotalPhisical());
        sell.setTotalVirtual(totals.getTotalVirtual());

        List<SellDetailCreateDTO> detailsDTO = sellCreateDTO.getDetails();
        this.sellDetailService.create(detailsDTO, sellId, companyId);

        sell = this.sellRepository.save(sell);
        return sell;
    }

    private void validate(SellCreateDTO sellCreateDTO) {
        Long sellerId = sellCreateDTO.getSellerId();
        Long tenantId = sellCreateDTO.getTenantId();
        Long companyId = sellCreateDTO.getCompanyId();

        this.validateTotal(sellCreateDTO);
        this.userService.validate(sellerId);
        this.tenantService.validate(tenantId);
        this.companyService.validate(companyId);
    }

    private void validateTotal(SellCreateDTO sellCreateDTO) {
        List<SellPayMethodCreateDTO> sellPayMethods = sellCreateDTO.getPayMethods();
        List<SellDetailCreateDTO> details = sellCreateDTO.getDetails();
        float total = (float) details.stream()
                .mapToDouble(SellDetailCreateDTO::getSubTotal)
                .sum();

        float totalFromSellPayMethods = (float) sellPayMethods.stream().mapToDouble(SellPayMethodCreateDTO::getAmount)
                .sum();

        if (totalFromSellPayMethods != total)
            throw new RuntimeException(
                    "La cantidad del total de los detalles de venta no concuerda con el total de las cantidades del método de pago");
    }

    @Transactional(rollbackFor = Exception.class)
    public Sell update(SellUpdateDTO sellUpdateDTO) {
        Sell sell = sellUpdateDTO.asEntity();
        return sell;
    }
}
