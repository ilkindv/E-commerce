package service.impl;

import helper.GeneralServiceHelper;
import model.constant.Constant;
import model.entity.Product;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import repository.ProductRepository;
import repository.impl.IProductRepository;
import service.ProductService;
import util.InputUtil;

import java.util.List;

import static helper.GeneralServiceHelper.fillProduct;

public class IProductService implements ProductService {
    ProductRepository productRepository = new IProductRepository();
    @Override
    public void creatProduct() {
        Product product = fillProduct();
        productRepository.createProduct(product);
        System.out.println(Constant.SAVE_SUCCESSFULLY);
    }

    @Override
    public void findAllProduct() {
        System.out.println(productRepository.findAll());
    }

    @Override
    public Product findById() {
        long id = InputUtil.getInstance().requiredLong("Enter the product ID: ");
        return productRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ExceptionEnum.PRODUCT_NOT_FOUND));
    }

    @Override
    public void findByName() {
        String name = InputUtil.getInstance().requiredString("Enter the product name: ");
        Product foundProduct = productRepository.findByName(name);
        System.out.println(foundProduct);
    }

    @Override
    public void updateProductForUser( String comment, double star, Product product) {
        product.setComment(comment);
        product.setStar(star);
        productRepository.update(product);
    }

    @Override
    public void updateProductForAdmin() {
        findAllProduct();
        long id = InputUtil.getInstance().requiredLong("Enter the product ID: ");
        Product product = productRepository.findById(id).orElseThrow(()
                -> new ApplicationException(ExceptionEnum.PRODUCT_NOT_FOUND));
        Product updatedProduct = GeneralServiceHelper.updateProduct(product);
        productRepository.update(updatedProduct);
    }

    @Override
    public void deleteProduct() {
        findAllProduct();
        long id = InputUtil.getInstance().requiredLong("Enter the product ID for deletion: ");
        Product deleteProduct = productRepository.findById(id).orElseThrow(()
                -> new ApplicationException(ExceptionEnum.PRODUCT_NOT_FOUND));
        productRepository.delete(deleteProduct);;
        System.out.println(Constant.DELETE_SUCCESSFULLY);
    }

    @Override
    public void deleteAllProduct() {
        List<Product> allCategories = productRepository.findAll();
        if (allCategories.isEmpty()) {
            throw new ApplicationException(ExceptionEnum.PRODUCT_NOT_FOUND);
        }
        productRepository.deleteAll(allCategories);
    }

    @Override
    public void decreaseRemainCount(List<Product> products) {
        products.stream()
                .peek(Product::decreaseRemainCount)
                .forEach(productRepository:: update);
    }
}
