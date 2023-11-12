package management.impl;

import management.AdminManagement;
import management.BrandManagement;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import service.BrandService;
import service.impl.IBrandService;
import util.MenuUtil;

public class IBrandManagement implements BrandManagement{
    @Override
    public void brandManage() {
        BrandService brandService = new IBrandService();
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
                        brandService.createBrand();
                        break;
                    case 3:
                        brandService.findAllBrand();
                        break;
                    case 4:
                        brandService.findById();
                        break;
                    case 5:
                        brandService.findByName();
                        break;
                    case 6:
                        brandService.updateBrand();
                        break;
                    case 7:
                        brandService.deleteBrand();
                        break;
                    case 8:
                        brandService.deleteAllBrand();
                        break;
                    default: throw new ApplicationException(ExceptionEnum.INVALID_OPTION_EXCEPTION);
                }
            } catch (RuntimeException exception){
                exception.printStackTrace();
            }
        }
    }
}
