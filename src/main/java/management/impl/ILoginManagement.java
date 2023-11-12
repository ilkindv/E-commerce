package management.impl;

import management.AdminManagement;
import management.LoginManagement;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import service.LoginService;
import service.impl.ILoginService;
import util.MenuUtil;

public class ILoginManagement implements LoginManagement {
    @Override
    public void loginManage() {
        AdminManagement adminManagement = new IAdminManagement();
        LoginService loginService = new ILoginService();

        while (true){
            try {
                byte option = MenuUtil.loginMenu();
                switch (option){
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        adminManagement.adminManage();
                        break;
                    case 2:
                        loginService.login();
                        break;
                    case 3:
                        loginService.register();
                        break;
                    default: throw new ApplicationException(ExceptionEnum.INVALID_OPTION_EXCEPTION);
                }
            }catch (RuntimeException exception){
                exception.printStackTrace();
            }
        }
    }
}
