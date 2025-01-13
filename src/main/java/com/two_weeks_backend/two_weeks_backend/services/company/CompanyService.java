package com.two_weeks_backend.two_weeks_backend.services.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.two_weeks_backend.two_weeks_backend.entities.company.Company;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;
import com.two_weeks_backend.two_weeks_backend.utils.specification.Specification;

@Service
public class CompanyService extends BaseServiceImplementation<Company> {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Company create(Company company) {
        String nameInTenant = company.getNameInTenant();

        boolean existsCompanyInTenant = this.existsCompanyInTenant(nameInTenant);
        if (existsCompanyInTenant)
            throw new RuntimeException("Ya existe la empresa " + company.getName());

        return this.baseRepository.save(company);
    }

    private boolean existsCompanyInTenant(String nameInTenant) {
        String attribute = "nameInTenant";
        String operator = "<eq>";
        String query = attribute + operator + nameInTenant;
        Specification<Company> specification = new Specification<>(query);

        Pageable pageable = PageRequest.of(0, 1);

        Page<Company> page = super.get(specification, pageable);
        return !page.isEmpty();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Company update(Company company) {
        Company retrievedCompany = this.baseRepository.getReferenceById(company.getId());

        if (retrievedCompany == null)
            throw new RuntimeException("No se ha encontrado la empresa");

        String nameInTenant = company.getName() + retrievedCompany.getTenant().getId();
        boolean existCompayInTenant = this.existsCompanyInTenant(nameInTenant);
        if (existCompayInTenant && !company.getName().equals(retrievedCompany.getName()))
            throw new RuntimeException("Ya existe la empresa" + company.getName());

        if (!retrievedCompany.getActivated())
            throw new RuntimeException("La empresa " + retrievedCompany.getName() + " está eliminada");

        if (company.getName().equals(retrievedCompany.getName())
                && company.getLogo().equals(retrievedCompany.getLogo())
                && company.getRUC().equals(retrievedCompany.getRUC())
                && company.getStandNumbers().equals(retrievedCompany.getStandNumbers())
                && company.getPlace().equals(retrievedCompany.getPlace()))
            return retrievedCompany;

        retrievedCompany.setName(company.getName());
        retrievedCompany.setLogo(company.getLogo());
        retrievedCompany.setRUC(company.getRUC());
        retrievedCompany.setStandNumbers(company.getStandNumbers());
        retrievedCompany.setPlace(company.getPlace());
        return this.baseRepository.save(retrievedCompany);
    }

    @Transactional(rollbackFor = Exception.class)
    public Company setActivation(Company company) {
        Company retrievedCompany = this.baseRepository.getReferenceById(company.getId());

        if (retrievedCompany == null)
            throw new RuntimeException("No se ha encontrado la compañía");

        if (company.getActivated() == retrievedCompany.getActivated())
            return retrievedCompany;

        retrievedCompany.setActivated(company.getActivated());
        return this.baseRepository.save(retrievedCompany);
    }

    public Company validate(Long companyId) {
        if (companyId == null)
            throw new RuntimeException("Identificador de la empresa no existe");

        Company company = this.baseRepository.getReferenceById(companyId);

        if (company == null)
            throw new RuntimeException("No existe la empresa con el identificador " + companyId);

        if (!company.getActivated())
            throw new RuntimeException("La empresa " + company.getName() + " está eliminada");

        return company;
    }
}
