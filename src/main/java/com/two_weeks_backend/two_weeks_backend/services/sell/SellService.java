package com.two_weeks_backend.two_weeks_backend.services.sell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.entities.sell.Sell;
import com.two_weeks_backend.two_weeks_backend.repositories.sell.SellRepository;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;
import com.two_weeks_backend.two_weeks_backend.services.company.CompanyService;
import com.two_weeks_backend.two_weeks_backend.services.tenant.TenantService;
import com.two_weeks_backend.two_weeks_backend.services.user.UserService;

@Service
public class SellService extends BaseServiceImplementation<Sell> {
    @Autowired
    private SellRepository sellRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private CompanyService companyService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Sell create(Sell sell) {
        Long sellerId = sell.getSeller().getId();
        this.userService.validate(sellerId);

        Long tenantId = sell.getTenant().getId();
        this.tenantService.validate(tenantId);

        Long companyId = sell.getCompany().getId();
        this.companyService.validate(companyId);

        return this.sellRepository.save(sell);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Sell update(Sell sell) {
        Sell retrievedSell = this.baseRepository.getReferenceById(sell.getId());

        if (retrievedSell == null)
            throw new RuntimeException("No se ha encontrado la venta");

        if (!retrievedSell.getActivated())
            throw new RuntimeException("La venta está eliminada");

        if (retrievedSell.getDate().equals(sell.getDate()) && retrievedSell.getClientName().equals(sell.getClientName())
                && retrievedSell.getClientDNI().equals(sell.getClientDNI())
                && retrievedSell.getClientRUC().equals(sell.getClientRUC())
                && retrievedSell.getDocumentType() == sell.getDocumentType()
                && retrievedSell.getTotal() == sell.getTotal()
                && retrievedSell.getTotalVirtual() == sell.getTotalVirtual()
                && retrievedSell.getTotalPhisical() == sell.getTotalPhisical()
                && retrievedSell.getIsReturned() == sell.getIsReturned()
                && retrievedSell.getIsChanged() == sell.getIsChanged())
            return retrievedSell;

        retrievedSell.setDate(sell.getDate());
        retrievedSell.setClientName(sell.getClientName());
        retrievedSell.setClientDNI(sell.getClientDNI());
        retrievedSell.setClientRUC(sell.getClientRUC());
        retrievedSell.setDocumentType(sell.getDocumentType());
        retrievedSell.setTotal(sell.getTotal());
        retrievedSell.setTotalVirtual(sell.getTotalVirtual());
        retrievedSell.setTotalPhisical(sell.getTotalPhisical());
        retrievedSell.setIsReturned(sell.getIsReturned());
        retrievedSell.setIsChanged(sell.getIsChanged());
        return retrievedSell;
    }

    @Transactional(rollbackFor = Exception.class)
    public Sell setActivation(Sell sell) {
        Sell retrievedSell = this.baseRepository.getReferenceById(sell.getId());

        if (retrievedSell == null)
            throw new RuntimeException("No se ha encontrado la venta");

        if (sell.getActivated() == retrievedSell.getActivated())
            return retrievedSell;

        retrievedSell.setActivated(sell.getActivated());
        return this.baseRepository.save(retrievedSell);
    }
}
