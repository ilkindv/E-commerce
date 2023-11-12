package service;

import model.entity.User;

public interface OrderService {
    void createOrder(User user);
    void finAllOrder();
}
