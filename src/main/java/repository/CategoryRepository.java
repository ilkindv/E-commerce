package repository;

import model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    void createCategory (Category category);
    List<Category> findAll();
    Category findByName(String name);
    Optional<Category> findById(long id);
    void update(Category category);
    boolean deleteAll(List<Category> categoryList);
    boolean delete(Category category);
}
