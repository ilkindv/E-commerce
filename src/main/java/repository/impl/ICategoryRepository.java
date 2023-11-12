package repository.impl;

import jakarta.persistence.TypedQuery;
import model.config.RepositoryConfig;
import model.entity.BaseEntity;
import model.entity.Category;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

public class ICategoryRepository extends RepositoryConfig implements CategoryRepository {
    @Override
    public void createCategory(Category category) {
        try{
            executeInTransaction(() -> getEntityManager().persist(category)); ;
        } catch (RuntimeException e) {
            throw new ApplicationException(ExceptionEnum.PERSIST_UNSUCCESSFULLY);
        }
    }

    @Override
    public List<Category> findAll() {
        TypedQuery<Category> typedQuery = getEntityManager().createNamedQuery("findAllCategories", Category.class);
        return typedQuery.getResultList();
    }

    @Override
    public Category findByName(String name) {
        TypedQuery<Category> typedQuery = getEntityManager().createNamedQuery("findCategoryByName", Category.class);
        typedQuery.setParameter("name", name);
        return Optional.ofNullable(typedQuery.getSingleResult()).orElseThrow(()
                -> new ApplicationException(ExceptionEnum.CATEGORY_NOT_FOUND));
    }

    @Override
    public Optional<Category> findById(long id) {
        return Optional.ofNullable(getEntityManager().find(Category.class, id));
    }

    @Override
    public void update(Category category) {
        try {
            executeInTransaction(() -> getEntityManager().merge(category));
        } catch (RuntimeException e) {
            throw new ApplicationException(ExceptionEnum.UPDATE_UNSUCCESSFULLY);
        }
    }

    @Override
    public boolean deleteAll(List<Category> categoryList) {
        for (BaseEntity entity : categoryList) {
            deleteEntity(entity);
            entity.setStatus(false);
        }
        return false;
    }

    @Override
    public boolean delete(Category category) {
        try {
            executeInTransaction(() -> getEntityManager().remove(category));
            category.setStatus(false);
            return true;
        } catch (RuntimeException exception) {
            throw new ApplicationException(ExceptionEnum.DELETE_UNSUCCESSFULLY);
        }
    }
}
