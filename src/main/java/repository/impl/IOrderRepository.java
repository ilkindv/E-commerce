package repository.impl;

import jakarta.persistence.TypedQuery;
import model.config.RepositoryConfig;
import model.entity.Order;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import repository.OrderRepository;

import java.util.List;
import java.util.Optional;

public class IOrderRepository extends RepositoryConfig implements OrderRepository  {
    @Override
    public void createOrder(Order order) {
        try {
            executeInTransaction(() -> getEntityManager().persist(order));
        } catch (RuntimeException e) {
            throw new ApplicationException(ExceptionEnum.PERSIST_UNSUCCESSFULLY);
        }
    }

    @Override
    public Optional<Order> findById(long id) {
        return Optional.ofNullable(getEntityManager().find(Order.class, id));
    }

    @Override
    public List<Order> findAll() {
        TypedQuery<Order> typedQuery = getEntityManager().createNamedQuery("findAllOrders", Order.class);
        return typedQuery.getResultList();
    }
}
