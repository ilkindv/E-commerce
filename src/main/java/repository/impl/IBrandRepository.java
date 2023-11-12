package repository.impl;

import jakarta.persistence.TypedQuery;
import model.config.RepositoryConfig;
import model.entity.BaseEntity;
import model.entity.Brand;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import repository.BrandRepository;

import java.util.List;
import java.util.Optional;

public class IBrandRepository extends RepositoryConfig implements BrandRepository {
    @Override
    public void createBrand(Brand brand) {
        try {
            executeInTransaction(() -> getEntityManager().persist(brand));
        } catch (RuntimeException e) {
            throw new ApplicationException(ExceptionEnum.PERSIST_UNSUCCESSFULLY);
        }
    }

    @Override
    public Optional<Brand> findById(long id) {
        return Optional.ofNullable(getEntityManager().find(Brand.class, id));
    }

    @Override
    public List<Brand> findAll() {
        TypedQuery<Brand> typedQuery = getEntityManager().createNamedQuery("findAllBrands", Brand.class);
        return typedQuery.getResultList();
    }

    @Override
    public Brand findByName(String name) {
        TypedQuery<Brand> typedQuery = getEntityManager().createNamedQuery("findBrandByName", Brand.class);
        typedQuery.setParameter("name", name);
        return Optional.ofNullable(typedQuery.getSingleResult()).orElseThrow(()
                -> new ApplicationException(ExceptionEnum.BRAND_NOT_FOUND));
    }

    @Override
    public void update(Brand brand) {
        try {
            executeInTransaction(() -> getEntityManager().merge(brand));
        } catch (RuntimeException e) {
            throw new ApplicationException(ExceptionEnum.UPDATE_UNSUCCESSFULLY);
        }
    }

    @Override
    public boolean deleteAll(List<Brand> brandList) {
        for (BaseEntity entity : brandList) {
            deleteEntity(entity);
            entity.setStatus(false);
        }
        return false;
    }

    @Override
    public boolean delete(Brand brand) {
        try {
            executeInTransaction(() -> getEntityManager().remove(brand));
            brand.setStatus(false);
            return true;
        } catch (RuntimeException exception) {
            throw new ApplicationException(ExceptionEnum.DELETE_UNSUCCESSFULLY);
        }
    }
}
