package repository.impl;

import model.config.RepositoryConfig;
import model.entity.Company;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import repository.CompanyRepository;

public class ICompanyRepository extends RepositoryConfig implements CompanyRepository {
    @Override
    public void updateCompany(Company company) {
        try {
            executeInTransaction(() -> getEntityManager().merge(company));
        } catch (RuntimeException e) {
            throw new ApplicationException(ExceptionEnum.UPDATE_UNSUCCESSFULLY);
        }
    }
    @Override
    public Company findCompanyById(long id) {
        return getEntityManager().find(Company.class,id);
    }
}
