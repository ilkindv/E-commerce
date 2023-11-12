package repository;

import model.entity.Cart;

import java.math.BigDecimal;
import java.util.List;

public interface CartRepository {
    List<Cart> findAllCart();
    Cart findCartByTotalAmount(BigDecimal amount);
    void buyProductsOnCart(Cart cart);
    void updateProductForCart(Cart cart);
}
