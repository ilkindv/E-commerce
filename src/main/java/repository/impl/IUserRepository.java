package repository.impl;

import jakarta.persistence.TypedQuery;
import model.config.RepositoryConfig;
import model.entity.BaseEntity;
import model.entity.User;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class IUserRepository extends RepositoryConfig implements UserRepository {
    @Override
    public boolean createUser(User user) {
        try {
            executeInTransaction(() -> getEntityManager().persist(user));
            return true;
        } catch (RuntimeException e) {
            throw new ApplicationException(ExceptionEnum.PERSIST_UNSUCCESSFULLY);
        }
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> typedQuery = getEntityManager().createNamedQuery("findAllUsers", User.class);
        return typedQuery.getResultList();
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> typedQuery = getEntityManager().createNamedQuery("findUserByUsername", User.class);
        typedQuery.setParameter("username", username);
        return Optional.ofNullable(typedQuery.getSingleResult()).orElseThrow(()
                -> new ApplicationException(ExceptionEnum.USER_NOT_FOUND));
    }

    @Override
    public User findByFinNumber(String finNumber) {
        TypedQuery<User> typedQuery = getEntityManager().createNamedQuery("findUserByFinNumber", User.class);
        typedQuery.setParameter("finNumber", finNumber);
        return Optional.ofNullable(typedQuery.getSingleResult()).orElseThrow(()
                -> new ApplicationException(ExceptionEnum.USER_NOT_FOUND));
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> typedQuery = getEntityManager().createNamedQuery("findUserByEmail", User.class);
        typedQuery.setParameter("accountEmail", email);
        return Optional.ofNullable(typedQuery.getSingleResult()).orElseThrow(()
                -> new ApplicationException(ExceptionEnum.USER_NOT_FOUND));
    }

    @Override
    public void update(User user) {
        try {
            executeInTransaction(() -> getEntityManager().merge(user));
        } catch (RuntimeException e) {
            throw new ApplicationException(ExceptionEnum.UPDATE_UNSUCCESSFULLY);
        }
    }

    @Override
    public boolean deleteAll(List<User> userList) {
        for (BaseEntity entity : userList) {
            deleteEntity(entity);
            entity.setStatus(false);
        }
        return false;
    }

    @Override
    public boolean delete(User user) {
        try {
            executeInTransaction(() -> getEntityManager().remove(user));
            user.setStatus(false);
            return true;
        } catch (RuntimeException exception) {
            throw new ApplicationException(ExceptionEnum.DELETE_UNSUCCESSFULLY);
        }
    }
}
