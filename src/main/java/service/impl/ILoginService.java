package service.impl;

import helper.GeneralServiceHelper;
import management.AdminManagement;
import management.UserManagement;
import management.impl.IAdminManagement;
import management.impl.IUserManagement;
import model.entity.User;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import service.LoginService;
import service.UserService;
import util.InputUtil;

import java.util.List;

public class ILoginService implements LoginService {
    UserService userService = new IUserService();
    UserManagement userManagement = new IUserManagement();
    AdminManagement adminManagement = new IAdminManagement();
    public void login() {
        System.out.println("--------------------/ Login Menu /--------------------");
        String email = InputUtil.getInstance().requiredString("Enter your email address: ");
        String password = InputUtil.getInstance().requiredString("Enter your password: ");

        List<User> users = userService.findAll();
        User loginedUser = users.stream()
                .filter(user -> user.getAccountEmail().equals(email))
                .filter(user -> user.getPassword().equals(password))
                .findFirst().orElseThrow(() -> new ApplicationException(ExceptionEnum.USER_NOT_FOUND));
        userManagement.userManage(loginedUser);
    }

    @Override
    public void register() {
        System.out.println("--------------------/ Register Menu /--------------------");
        User registeredUser = GeneralServiceHelper.fillUser();

        userService.createUser(registeredUser);
        userManagement.userManage(registeredUser);
    }

    @Override
    public void adminLogin() {
        System.out.println("--------------------/ Admin Login Menu /--------------------");
        String username = InputUtil.getInstance().requiredString("Enter your email address: ");
        String password = InputUtil.getInstance().requiredString("Enter your password: ");

        if(username.equals("admin") && password.equals("admin")){
            adminManagement.adminManage();
        }else throw new ApplicationException(ExceptionEnum.WRONG_USERNAME_OR_PASSWORD);
    }
}
