package management.impl;

import management.*;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import util.MenuUtil;

public class IAdminManagement implements AdminManagement {
    @Override
    public void adminManage() {
        CategoryManagement categoryManagement = new ICategoryManagement();
        BrandManagement brandManagement = new IBrandManagement();
        ProductManagement productManagement = new IProductManagement();
        OrderManagement orderManagement = new IOrderManagement();
        Management management = new IManagement();
        while (true){
            try {
                byte option = MenuUtil.adminPanel();
                switch (option) {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        management.manage();
                    case 2:
                        categoryManagement.categoryManage();
                        break;
                    case 3:
                        brandManagement.brandManage();
                        break;
                    case 4:
                        productManagement.productManage();
                        break;
                    case 5:
                        orderManagement.orderManage();
                        break;
                    default: throw new ApplicationException(ExceptionEnum.INVALID_OPTION_EXCEPTION);
                }
            }catch (RuntimeException exception) {
                exception.printStackTrace();
            }
        }
    }
}
