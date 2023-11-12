package management.impl;

import management.LoginManagement;
import management.UserManagement;
import model.entity.User;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import service.UserService;
import service.impl.IUserService;
import util.MenuUtil;

public class IUserManagement implements UserManagement {
    @Override
    public void userManage(User user) {
        UserService userService = new IUserService();
        LoginManagement loginManagement = new ILoginManagement();

        while (true) {
            try{
                byte option = MenuUtil.customerMenu();
                switch (option) {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        loginManagement.loginManage();
                        break;
                    case 2:
                        userService.findAllProduct();
                        break;
                    case 3:
                        userService.addProductToCart(user);
                        break;
                    case 4:
                        userService.deleteProductToCart(user);
                    case 5:
                        userService.buyProductToCart(user);
                        break;
                    case 6:
                        userService.increaseBalance(user);
                        break;
                    default: throw new ApplicationException(ExceptionEnum.INVALID_OPTION_EXCEPTION);
                }
            }catch (RuntimeException exception){
                exception.printStackTrace();
            }
        }
    }
}
