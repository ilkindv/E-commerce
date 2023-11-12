package service.impl;

import helper.GeneralServiceHelper;
import model.entity.Cart;
import model.entity.Product;
import model.entity.User;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import repository.UserRepository;
import repository.impl.IUserRepository;
import service.*;
import util.InputUtil;

import java.math.BigDecimal;
import java.util.List;

import static model.constant.Constant.*;
public class IUserService implements UserService {
    UserRepository userRepository = new IUserRepository();
    CartService cartService = new ICartService();
    ProductService productService = new IProductService();
    CompanyService companyService = new ICompanyService();
    OrderService orderService = new IOrderService();


    @Override
    public void createUser(User user) {
        userRepository.createUser(user);
    }

    @Override
    public void updateAccount(User user, BigDecimal totalPrice) {
        user.setUserBalance(user.getUserBalance().subtract(totalPrice));
        userRepository.update(user);
    }

    @Override
    public void buyProductToCart(User user) {
        if (user.getCart().getBuyProduct().isEmpty()) {
            throw new ApplicationException(ExceptionEnum.CART_IS_EMPTY);
        }

        Cart cart = user.getCart();
        GeneralServiceHelper.checkUserBalance(user, cart.getTotalAmount());
        cartService.findAllProducts(user);

        Product product = productService.findById();
        String comment = InputUtil.getInstance().requiredString("Enter a comment: ");
        double star = InputUtil.getInstance().requiredDouble("Enter a star: ");
        productService.updateProductForUser(comment, star, product);

        productService.decreaseRemainCount(cart.getBuyProduct());
        companyService.updateCompanyAccount(cart.getTotalAmount());
        updateAccount(user, cart.getTotalAmount());
        orderService.createOrder(user);
        cartService.buyProducts(cart);
        System.out.println(PAYMENT_SUCCESSFULLY);
    }

    @Override
    public void addProductToCart(User user) {
        productService.findAllProduct();
        Product product = productService.findById();
        Cart cart = user.getCart();
        cartService.addProduct(product, cart);
    }

    @Override
    public void deleteProductToCart(User user) {
        if (user.getCart().getBuyProduct().isEmpty()) {
            throw new ApplicationException(ExceptionEnum.CART_IS_EMPTY);
        }
        cartService.findAllProducts(user);
        Product product = productService.findById();
        Cart cart = user.getCart();
        cartService.removeProduct(product, cart);
    }

    @Override
    public void findAllProduct() {
        productService.findAllProduct();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void increaseBalance(User user) {
        BigDecimal addingBalance = InputUtil.getInstance().requiredBigDecimal("Enter the amount to increase balance: ");
        user.setUserBalance(user.getUserBalance().add(addingBalance));
    }
}
