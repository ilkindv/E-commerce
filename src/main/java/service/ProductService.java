package service;

import model.entity.Product;

import java.util.List;

public interface ProductService {
    void creatProduct();
    void findAllProduct();
    Product findById();
    void findByName();
    void updateProductForUser(String comment,double star, Product product);
    void updateProductForAdmin();
    void deleteProduct();
    void deleteAllProduct();
    void decreaseRemainCount(List<Product> products);
}
