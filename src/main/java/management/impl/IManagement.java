package management.impl;

import management.LoginManagement;
import management.Management;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import service.LoginService;
import service.impl.ILoginService;
import util.MenuUtil;

public class IManagement implements Management {
    @Override
    public void manage() {
        LoginService loginService = new ILoginService();
        LoginManagement loginManagement = new ILoginManagement();

        while (true){
            try {
                byte option = MenuUtil.entryMenu();
                switch (option){
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        loginService.adminLogin();
                        break;
                    case 2:
                        loginManagement.loginManage();
                        break;
                    default: throw new ApplicationException(ExceptionEnum.INVALID_OPTION_EXCEPTION);
                }
            }catch (RuntimeException exception){
                exception.printStackTrace();
            }
        }
    }
}
