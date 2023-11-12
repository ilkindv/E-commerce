package service.impl;

import model.constant.Constant;
import model.entity.Brand;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import repository.BrandRepository;
import repository.impl.IBrandRepository;
import service.BrandService;
import util.InputUtil;

import java.util.List;
import java.util.Optional;

import static helper.GeneralServiceHelper.fillBrand;

public class IBrandService implements BrandService {
    BrandRepository brandRepository = new IBrandRepository();

    @Override
    public void createBrand() {
        Brand brand = fillBrand();
        brandRepository.createBrand(brand);
        System.out.println(Constant.SAVE_SUCCESSFULLY);
    }

    @Override
    public void findAllBrand() {
        System.out.println(brandRepository.findAll());
    }

    @Override
    public Brand findById() {
        long id = InputUtil.getInstance().requiredLong("Enter the brand ID: ");
        Optional<Brand> findById = brandRepository.findById(id);
        return findById.orElseThrow(() -> new ApplicationException(ExceptionEnum.BRAND_NOT_FOUND));
    }

    @Override
    public void findByName() {
        String name = InputUtil.getInstance().requiredString("Enter the brand name: ");
        Brand findByName = brandRepository.findByName(name);
        System.out.println(findByName);
    }

    @Override
    public void updateBrand() {
        System.out.println(brandRepository.findAll());
        long id = InputUtil.getInstance().requiredLong("Enter the brand ID for update: ");
        Brand updateBrand = brandRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ExceptionEnum.BRAND_NOT_FOUND));
        String newName = InputUtil.getInstance().requiredString("Enter the new brand name: ");
        updateBrand.setName(newName);
        String newDescription = InputUtil.getInstance().requiredString("Enter the new brand description: ");
        updateBrand.setDescription(newDescription);
        System.out.println(Constant.UPDATE_SUCCESSFULLY);
    }

    @Override
    public void deleteBrand() {
        System.out.println(brandRepository.findAll());
        long id = InputUtil.getInstance().requiredLong("Enter the brand ID for delete: ");
        Brand deletedBrand = brandRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ExceptionEnum.BRAND_NOT_FOUND));
        brandRepository.delete(deletedBrand);
        System.out.println(Constant.DELETE_SUCCESSFULLY);
    }

    @Override
    public void deleteAllBrand() {
        List<Brand> allBrand = brandRepository.findAll();
        if (allBrand.isEmpty()) {
            throw new ApplicationException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        brandRepository.deleteAll(allBrand);
    }
}
