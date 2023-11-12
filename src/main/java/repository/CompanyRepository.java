package repository;

import model.entity.Company;

public interface CompanyRepository {
    void updateCompany(Company company);
    Company findCompanyById(long id);
}
