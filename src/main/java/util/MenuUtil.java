package util;

import lombok.Getter;

public class MenuUtil {
    @Getter
    private static final MenuUtil instance = new MenuUtil();

    public static byte entryMenu(){
        System.out.println("""
                ---------/ E-Commerce System /---------
                [0] - > Exit
                [1] - > Admin
                [2] - > User
                """);
        return InputUtil.getInstance().requiredByte("Choose the option: ");
    }

    public static byte adminPanel(){
        System.out.println("""
                ------------------- / Admin Menu /------------------
                [0] - > Exit
                [1] - > Back
                [2] - > Category
                [3] - > Brand
                [4] - > Product
                [5] - > Order
                """);
        return InputUtil.getInstance().requiredByte("Choose the option: ");
    }

    public static byte categoryMenu(){
        System.out.println("""
                ------------------- / Category Menu /------------------
                [0] - > Exit
                [1] - > Back
                [2] - > Save Category
                [3] - > View All Category
                [4] - > Find by ID
                [5] - > Find by Name
                [6] - > Update Category
                [7] - > Delete Category
                [8] - > Delete All Categories
                """);
        return InputUtil.getInstance().requiredByte("Choose the option: ");
    }

    public static byte brandMenu(){
        System.out.println("""
                ------------------- / Brand Menu /------------------
                [0] - > Exit
                [1] - > Back
                [2] - > Save Brand
                [3] - > View All Brand
                [4] - > Find by ID
                [5] - > Find by Name
                [6] - > Update Brand
                [7] - > Delete Brand
                [8] - > Delete All Brands
                """);
        return InputUtil.getInstance().requiredByte("Choose the option: ");
    }

    public static byte productMenu(){
        System.out.println("""
                ------------------- / Product Menu /------------------
                [0] - > Exit
                [1] - > Back
                [2] - > Save Product
                [3] - > View All Product
                [4] - > Find by ID
                [5] - > Find by Name
                [6] - > Update Product
                [7] - > Delete Product
                [8] - > Delete All Products
                """);
        return InputUtil.getInstance().requiredByte("Choose the option: ");
    }

    public static byte orderMenu(){
        System.out.println("""
                ------------------- / Order Menu /------------------
                [1] - > Back
                [2] - > View All Order
                """);
        return InputUtil.getInstance().requiredByte("Choose the option: ");
    }

    public static byte customerMenu(){
        System.out.println("""
                ------------------- / User Menu /------------------
                [0] - > Exit
                [1] - > back
                [2] - > Show all product
                [3] - > add product to cart
                [4] - > delete product from cart
                [5] - > buy product on cart
                [6] - > increase balance
                """);
        return InputUtil.getInstance().requiredByte("Choose the option: ");
    }

    public static byte loginMenu(){
        System.out.println("""
                ------------------- / Login Menu /------------------
                [0] - > Exit
                [1] - > Back
                [2] - > Login
                [3] - > Register
                """);
        return InputUtil.getInstance().requiredByte("Choose the option: ");
    }
}
