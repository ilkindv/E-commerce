package repository;

import model.entity.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandRepository {
    void createBrand(Brand brand);
    Optional<Brand> findById(long id);
    List<Brand> findAll();
    Brand findByName(String name);
    void update(Brand brand);
    boolean deleteAll(List<Brand> brandList);
    boolean delete(Brand brand);
}
