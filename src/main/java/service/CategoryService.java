package service;

import model.entity.Category;

public interface CategoryService {
    void createCategory();
    void findAllCategory();
    Category findById();
    void findByName();
    void updateCategory();
    void deleteCategory();
    void deleteAllCategory();
}
