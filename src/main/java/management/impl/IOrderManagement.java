package management.impl;

import management.AdminManagement;
import management.OrderManagement;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import service.OrderService;
import service.impl.IOrderService;
import util.MenuUtil;

public class IOrderManagement implements OrderManagement {
    @Override
    public void orderManage() {
        AdminManagement adminManagement = new IAdminManagement();
        OrderService orderService = new IOrderService();

        while (true){
            try {
                byte option = MenuUtil.orderMenu();
                switch (option) {
                    case 1:
                        adminManagement.adminManage();
                        break;
                    case 2:
                        orderService.finAllOrder();
                        break;
                    default: throw new ApplicationException(ExceptionEnum.INVALID_OPTION_EXCEPTION);
                }
            }catch (RuntimeException exception) {
                exception.printStackTrace();
            }
        }
    }
}
