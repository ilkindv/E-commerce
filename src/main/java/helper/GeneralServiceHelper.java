package helper;

import model.entity.*;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import repository.CartRepository;
import repository.impl.ICartRepository;
import service.BrandService;
import service.CategoryService;
import service.impl.IBrandService;
import service.impl.ICategoryService;
import util.InputUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class GeneralServiceHelper {
    static BrandService brandService = new IBrandService();
    static CategoryService categoryService = new ICategoryService();
    static CartRepository cartRepository = new ICartRepository();

    public static Category fillCategory(){
        String name = InputUtil.getInstance().requiredString("Enter the category name: ");
        String description = InputUtil.getInstance().requiredString("Enter the category description: ");

        return Category.builder()
                .name(name)
                .description(description)
                .build();
    }

    public static Brand fillBrand(){
        String name = InputUtil.getInstance().requiredString("Enter the brand name: ");
        String description = InputUtil.getInstance().requiredString("Enter the brand description: ");

        return Brand.builder()
                .name(name)
                .description(description)
                .build();
    }

    public static Product fillProduct() {
        Brand brand = selectBrand();
        Category category = selectCategory();

        String name = InputUtil.getInstance().requiredString("Enter the product name: ");
        String description = InputUtil.getInstance().requiredString("Enter the product description: ");
        BigDecimal price = InputUtil.getInstance().requiredBigDecimal("Enter the product price: ");

        ProductDetail productDetails = new ProductDetail();
        productDetails.setColor(InputUtil.getInstance().requiredString("Enter the product color: "));
        productDetails.setSize(InputUtil.getInstance().requiredString("Enter the product size: "));
        productDetails.setCount(InputUtil.getInstance().requiredInt("Enter the stock count: "));

        int totalCount = productDetails.getCount();

        return Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .comment(new ArrayList<>())
                .remainCount(totalCount)
                .category(category)
                .brand(brand)
                .productDetails(productDetails)
                .build();
    }

    private static Brand selectBrand() {
        brandService.findAllBrand();
        return brandService.findById();
    }

    private static Category selectCategory() {
        categoryService.findAllCategory();
        return categoryService.findById();
    }

    public static Product updateProduct(Product product){
        String name = InputUtil.getInstance().requiredString("Enter the product new name: ");
        String description = InputUtil.getInstance().requiredString("Enter the product new description: ");
        BigDecimal price = InputUtil.getInstance().requiredBigDecimal("Enter the product new price: ");

        ProductDetail productDetails = new ProductDetail();
        productDetails.setColor(InputUtil.getInstance().requiredString("Enter the product new color: "));
        productDetails.setSize(InputUtil.getInstance().requiredString("Enter the product new size: "));
        productDetails.setCount(InputUtil.getInstance().requiredInt("Enter the stock new count: "));

        int totalCount = productDetails.getCount();

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setProductDetails(productDetails);
        product.setRemainCount(totalCount);

        return product;
    }

    public static void updateCart(Cart cart) {
        cart.setCount(cart.getBuyProduct().size());
        BigDecimal totalPrice = cart.sumProductsPriceOnCart(cart.getBuyProduct());
        cart.setTotalAmount(totalPrice);
        cartRepository.updateProductForCart(cart);
    }

    public static void resetCart(Cart cart) {
        cart.setCount(0);
        cart.setTotalAmount(BigDecimal.ZERO);
        cartRepository.updateProductForCart(cart);
    }

    public static void checkUserBalance(User user, BigDecimal totalPrice) {
        if (user.getUserBalance().compareTo(totalPrice) < 0) {
            throw new ApplicationException(ExceptionEnum.INSUFFICIENT_BALANCE);
        }
    }

    public static User fillUser(){
        String name = InputUtil.getInstance().requiredString("Enter your name: ");
        String surname = InputUtil.getInstance().requiredString("Enter your surname: ");
        LocalDate birthDate = LocalDate.parse(InputUtil.getInstance().requiredString("Enter your birth date: "));
        String finNumber = InputUtil.getInstance().requiredString("Enter your fin number: ");
        String address = InputUtil.getInstance().requiredString("Enter your address: ");
        String username = InputUtil.getInstance().requiredString("Enter your username: ");
        String email = InputUtil.getInstance().requiredString("Enter your email address: ");
        String password = InputUtil.getInstance().requiredString("Enter your password: ");

        User user = User.builder()
                .name(name)
                .surname(surname)
                .birthdate(birthDate)
                .finNumber(finNumber)
                .address(address)
                .username(username)
                .accountEmail(email)
                .password(password)
                .userBalance(BigDecimal.ZERO)
                .build();
        return user;
    }

}
