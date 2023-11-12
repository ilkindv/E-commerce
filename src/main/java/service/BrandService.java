package service;

import model.entity.Brand;

public interface BrandService {
    void createBrand();
    void findAllBrand();
    Brand findById();
    void findByName();
    void updateBrand();
    void deleteBrand();
    void deleteAllBrand();
}
