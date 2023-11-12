package management.impl;

import management.AdminManagement;
import management.ProductManagement;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import service.ProductService;
import service.impl.IProductService;
import util.MenuUtil;

public class IProductManagement implements ProductManagement {
    @Override
    public void productManage() {
        ProductService productService = new IProductService();
        AdminManagement adminManagement = new IAdminManagement();

        while (true){
            try {
                byte option = MenuUtil.productMenu();
                switch (option) {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        adminManagement.adminManage();
                        break;
                    case 2:
                        productService.creatProduct();
                        break;
                    case 3:
                        productService.findAllProduct();
                        break;
                    case 4:
                        productService.findById();
                        break;
                    case 5:
                        productService.findByName();
                        break;
                    case 6:
                        productService.updateProductForAdmin();
                        break;
                    case 7:
                        productService.deleteProduct();
                        break;
                    case 8:
                        productService.deleteAllProduct();
                        break;
                    default: throw new ApplicationException(ExceptionEnum.INVALID_OPTION_EXCEPTION);
                }
            }catch (RuntimeException exception) {
                exception.printStackTrace();
            }
        }
    }
}
