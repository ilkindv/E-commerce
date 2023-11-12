package service.impl;

import model.entity.Company;
import repository.CompanyRepository;
import repository.impl.ICompanyRepository;
import service.CompanyService;

import java.math.BigDecimal;

public class ICompanyService implements CompanyService {
    CompanyRepository companyRepository = new ICompanyRepository();
    @Override
    public void updateCompanyAccount(BigDecimal allSellingPrice) {
        Company company = companyRepository.findCompanyById(1);
        BigDecimal CompanyAccount = company.getTotalAmount().add(allSellingPrice);
        company.setTotalAmount(CompanyAccount);
        companyRepository.updateCompany(company);
    }
}
