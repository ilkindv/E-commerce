package model.config;

import jakarta.persistence.*;
import lombok.Getter;
import model.entity.BaseEntity;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;


@Getter
public class RepositoryConfig {
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private final EntityTransaction entityTransaction;

    public RepositoryConfig() {
        entityManagerFactory = Persistence.createEntityManagerFactory("postgres");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    public void executeInTransaction(Runnable action) {
        try {
            getEntityTransaction().begin();
            action.run();
            getEntityTransaction().commit();
        } catch (RuntimeException exception) {
            getEntityTransaction().rollback();
            throw exception;
        }
    }

    public void deleteEntity(BaseEntity entity) throws ApplicationException {
        try {
            getEntityTransaction().begin();
            getEntityManager().remove(entity);
            getEntityTransaction().commit();
        } catch (RuntimeException exception) {
            getEntityTransaction().rollback();
            throw new ApplicationException(ExceptionEnum.DELETE_UNSUCCESSFULLY);
        }
    }
}
