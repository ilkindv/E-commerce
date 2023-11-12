package repository.impl;


import jakarta.persistence.TypedQuery;
import model.config.RepositoryConfig;

import model.entity.Cart;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import repository.CartRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public class ICartRepository extends RepositoryConfig implements CartRepository {

    @Override
    public List<Cart> findAllCart() {
        TypedQuery<Cart> typedQuery = getEntityManager().createNamedQuery("findAllCarts", Cart.class);
        return typedQuery.getResultList();
    }

    @Override
    public Cart findCartByTotalAmount(BigDecimal amount) {
        TypedQuery<Cart> typedQuery = getEntityManager().createNamedQuery("findCartByTotalAmount", Cart.class);
        typedQuery.setParameter("totalAmount", amount);
        return Optional.ofNullable(typedQuery.getSingleResult()).orElseThrow(()
                -> new ApplicationException(ExceptionEnum.CART_NOT_FOUND));
    }

    @Override
    public void buyProductsOnCart(Cart cart) {
        try {
            executeInTransaction(() -> getEntityManager().persist(cart));
        } catch (RuntimeException e) {
            throw new ApplicationException(ExceptionEnum.PERSIST_UNSUCCESSFULLY);
        }
    }

    @Override
    public void updateProductForCart(Cart cart) {
        try {
            executeInTransaction(() -> getEntityManager().merge(cart));
        } catch (RuntimeException e) {
            throw new ApplicationException(ExceptionEnum.UPDATE_UNSUCCESSFULLY);
        }
    }
}
