package service;

import model.entity.Cart;
import model.entity.Product;
import model.entity.User;

public interface CartService {
    void addProduct(Product product, Cart cart);
    void findAllProducts(User user);
    void buyProducts(Cart cart);
    void removeProduct(Product product, Cart cart);

}
