package management.impl;

import management.AdminManagement;
import management.CategoryManagement;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import service.CategoryService;
import service.impl.ICategoryService;
import util.MenuUtil;

public class ICategoryManagement implements CategoryManagement {
    @Override
    public void categoryManage() {
        CategoryService categoryService = new ICategoryService();
        AdminManagement adminManagement = new IAdminManagement();
        while (true){
            try {
                byte option = MenuUtil.brandMenu();
                switch (option){
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        adminManagement.adminManage();
                        break;
                    case 2:
                        categoryService.createCategory();
                        break;
                    case 3:
                        categoryService.findAllCategory();
                        break;
                    case 4:
                        categoryService.findById();
                        break;
                    case 5:
                        categoryService.findByName();
                        break;
                    case 6:
                        categoryService.updateCategory();
                        break;
                    case 7:
                        categoryService.deleteCategory();
                        break;
                    case 8:
                        categoryService.deleteAllCategory();
                        break;
                    default: throw new ApplicationException(ExceptionEnum.INVALID_OPTION_EXCEPTION);
                }
            } catch (RuntimeException exception){
                exception.printStackTrace();
            }
        }
    }
}
