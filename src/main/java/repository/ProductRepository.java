package repository;

import model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    boolean createProduct(Product product);
    List<Product> findAll();
    Product findByName(String name);
    Optional<Product> findById(long id);
    void update(Product product);
    boolean deleteAll(List<Product> productList);
    boolean delete(Product product);
}
