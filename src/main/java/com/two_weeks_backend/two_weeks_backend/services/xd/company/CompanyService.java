package com.two_weeks_backend.two_weeks_backend.services.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.entities.company.Company;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;
import com.two_weeks_backend.two_weeks_backend.services.product.BarCodeService;

@Service
public class CompanyService extends BaseServiceImplementation<Company> {
    @Autowired
    BarCodeService barCodeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Company create(Company company) {
        company = this.baseRepository.save(company);
        this.barCodeService.create(company);
        return company;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Company update(Company company) {
        Company retrievedCompany = this.baseRepository.getReferenceById(company.getId());

        boolean hasChanges = !company.getName().trim().equals(retrievedCompany.getName())
                || company.getLogo().trim().equals(retrievedCompany.getLogo())
                || company.getCompanyCode().trim().equals(retrievedCompany.getCompanyCode())
                || company.getCountryCode().trim().equals(retrievedCompany.getCountryCode());
        if (!hasChanges)
            return company;

        retrievedCompany.setName(company.getName());
        retrievedCompany.setLogo(company.getLogo());
        retrievedCompany.setCompanyCode(company.getCompanyCode());
        retrievedCompany.setCountryCode(company.getCountryCode());

        return this.baseRepository.save(retrievedCompany);
    }

    @Transactional(rollbackFor = Exception.class)
    public Company setActivation(Company company) {
        Company retrievedCompany = this.baseRepository.getReferenceById(company.getId());

        boolean hasChanges = company.getActivated() != retrievedCompany.getActivated();
        if (hasChanges)
            return retrievedCompany;

        retrievedCompany.setActivated(company.getActivated());

        return this.baseRepository.save(retrievedCompany);
    }
}
