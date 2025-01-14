package com.two_weeks_backend.two_weeks_backend.services.sell;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell_pay_method.SellPayMethodCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Sell;
import com.two_weeks_backend.two_weeks_backend.entities.sell.SellPayMethod;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Totals;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.PayMethod;
import com.two_weeks_backend.two_weeks_backend.repositories.sell.SellPayMethodRepository;
import com.two_weeks_backend.two_weeks_backend.services.tenant.PayMethodService;

@Service
public class SellPayMethodService {
    @Autowired
    private SellPayMethodRepository sellPayMethodRepository;

    @Autowired
    private PayMethodService payMethodService;

    public List<SellPayMethod> create(List<SellPayMethodCreateDTO> sellPayMethodsCreateDTO, Long sellId) {
        if (sellId == null)
            throw new RuntimeException("Para crear los métodos de pagos de la venta debe existir la venta");
        Sell sell = new Sell();
        sell.setId(sellId);

        List<SellPayMethodCreateDTO> uniquePayMethodsDTO = new ArrayList<>(
                sellPayMethodsCreateDTO.stream()
                        .collect(Collectors.toMap(
                                SellPayMethodCreateDTO::getPayMethodId,
                                dto -> dto,
                                (dto1, dto2) -> {
                                    dto1.setAmount(dto1.getAmount() + dto2.getAmount());
                                    return dto1;
                                }))
                        .values());

        List<SellPayMethod> savedSellPayMethods = uniquePayMethodsDTO.stream()
                .map(uniquePayMethodDTO -> {
                    PayMethod payMethod = this.payMethodService.validate(uniquePayMethodDTO.getPayMethodId());

                    if (!payMethod.getIsJustForSell()) {
                        String messageError = "El método de pago " + payMethod.getName()
                                + " no está permitido para ventas";
                        throw new RuntimeException(messageError);
                    }

                    SellPayMethod sellPayMethod = uniquePayMethodDTO.asEntity();
                    sellPayMethod.setSell(sell);
                    sellPayMethod = this.sellPayMethodRepository.save(sellPayMethod);
                    sellPayMethod.setPayMethod(payMethod);

                    return sellPayMethod;
                })
                .collect(Collectors.toList());

        return savedSellPayMethods;
    }

    public Totals getTotals(List<SellPayMethod> sellPayMethods) {
        Totals totals = new Totals();

        for (SellPayMethod sellPayMethod : sellPayMethods) {
            float amount = sellPayMethod.getAmount();
            PayMethod payMethod = sellPayMethod.getPayMethod();
            if (payMethod == null)
                throw new RuntimeException("El método de pago debe de existir");

            if (payMethod.getIsVirtual() == null)
                throw new RuntimeException("El método de pago tener definido si es virtual o es físico");

            totals.setTotal(totals.getTotal() + amount);

            if (payMethod.getIsVirtual()) {
                totals.setTotalVirtual(totals.getTotalVirtual() + amount);
            } else {
                totals.setTotalPhisical(totals.getTotalPhisical() + amount);
            }
        }

        return totals;
    }
}
