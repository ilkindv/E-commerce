package service.impl;

import helper.GeneralServiceHelper;
import model.entity.Cart;
import model.entity.Product;
import model.entity.User;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import repository.CartRepository;
import repository.impl.ICartRepository;
import service.CartService;

public class ICartService implements CartService {
    CartRepository cartRepository = new ICartRepository();
    @Override
    public void addProduct(Product product, Cart cart) {
        cart.addProduct(product);
        GeneralServiceHelper.updateCart(cart);
    }

    @Override
    public void findAllProducts(User user) {
        System.out.println(user.getCart().getBuyProduct());
    }

    @Override
    public void buyProducts(Cart cart) {
        if (cart.getBuyProduct().isEmpty()) {
            throw new ApplicationException(ExceptionEnum.CART_IS_EMPTY);
        }
        cartRepository.buyProductsOnCart(cart);
        GeneralServiceHelper.resetCart(cart);
    }

    @Override
    public void removeProduct(Product product, Cart cart) {
        cart.deleteProduct(product);
        GeneralServiceHelper.updateCart(cart);
    }
}
