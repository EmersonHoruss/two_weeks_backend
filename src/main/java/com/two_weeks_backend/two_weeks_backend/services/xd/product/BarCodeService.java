package com.two_weeks_backend.two_weeks_backend.services.product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.two_weeks_backend.two_weeks_backend.entities.company.Company;
import com.two_weeks_backend.two_weeks_backend.entities.product.BarCode;
import com.two_weeks_backend.two_weeks_backend.repositories.product.BarCodeRepository;

@Service
public class BarCodeService {
    @Autowired
    private BarCodeRepository barCodeRepository;

    public void create(Company company) {
        BarCode barCode = new BarCode(company);

        barCode = this.barCodeRepository.save(barCode);
    }

    public BarCode getByCompanyId(Long companyId) {
        Optional<BarCode> barCode = this.barCodeRepository.findFirstByCompanyId(companyId);

        return barCode.orElse(null);
    }

    public void update(BarCode barCode) {
        this.barCodeRepository.save(barCode);
    }

}
