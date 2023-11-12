package repository;

import model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    void createOrder(Order order);
    Optional<Order> findById(long id);
    List<Order> findAll();

}
