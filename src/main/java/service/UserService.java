package service;

import model.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    void createUser(User user);
    void updateAccount(User user, BigDecimal totalPrice);
    void buyProductToCart(User user);
    void addProductToCart(User user);
    void deleteProductToCart(User user);
    void findAllProduct();
    List<User> findAll();
    void increaseBalance(User user);
}
