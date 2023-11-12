package repository.impl;

import jakarta.persistence.TypedQuery;
import model.config.RepositoryConfig;
import model.entity.BaseEntity;
import model.entity.Product;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import repository.ProductRepository;

import java.util.List;
import java.util.Optional;

public class IProductRepository extends RepositoryConfig implements ProductRepository {
    @Override
    public boolean createProduct(Product product) {
        try {
            executeInTransaction(() -> getEntityManager().persist(product));
            return true;
        } catch (RuntimeException e) {
            throw new ApplicationException(ExceptionEnum.PERSIST_UNSUCCESSFULLY);
        }
    }

    @Override
    public List<Product> findAll() {
        TypedQuery<Product> typedQuery = getEntityManager().createNamedQuery("findAllProducts", Product.class);
        return typedQuery.getResultList();
    }

    @Override
    public Product findByName(String name) {
        TypedQuery<Product> typedQuery = getEntityManager().createNamedQuery("findProductByName", Product.class);
        typedQuery.setParameter("name", name);
        return Optional.ofNullable(typedQuery.getSingleResult()).orElseThrow(()
                -> new ApplicationException(ExceptionEnum.CATEGORY_NOT_FOUND));
    }

    @Override
    public Optional<Product> findById(long id) {
        return Optional.ofNullable(getEntityManager().find(Product.class, id));
    }

    @Override
    public void update(Product product) {
        try {
            executeInTransaction(() -> getEntityManager().merge(product));
        } catch (RuntimeException e) {
            throw new ApplicationException(ExceptionEnum.UPDATE_UNSUCCESSFULLY);
        }
    }

    @Override
    public boolean deleteAll(List<Product> productList) {
        for (BaseEntity entity : productList) {
            deleteEntity(entity);
            entity.setStatus(false);
        }
        return false;
    }

    @Override
    public boolean delete(Product product) {
        try {
            executeInTransaction(() -> getEntityManager().remove(product));
            product.setStatus(false);
            return true;
        } catch (RuntimeException exception) {
            throw new ApplicationException(ExceptionEnum.DELETE_UNSUCCESSFULLY);
        }
    }
}
