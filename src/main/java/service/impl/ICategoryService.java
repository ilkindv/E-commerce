package service.impl;

import model.constant.Constant;
import model.entity.Category;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;
import repository.CategoryRepository;
import repository.impl.ICategoryRepository;
import service.CategoryService;
import util.InputUtil;

import java.util.List;
import java.util.Optional;

import static helper.GeneralServiceHelper.fillCategory;

public class ICategoryService implements CategoryService {
    CategoryRepository categoryRepository = new ICategoryRepository();

    @Override
    public void createCategory() {
        Category category = fillCategory();
        categoryRepository.createCategory(category);
        System.out.println(Constant.SAVE_SUCCESSFULLY);
    }

    @Override
    public void findAllCategory() {
        System.out.println(categoryRepository.findAll());
    }

    @Override
    public Category findById() {
        long id = InputUtil.getInstance().requiredLong("Enter the category ID: ");
        Optional<Category> foundCategory = categoryRepository.findById(id);
        return foundCategory.orElseThrow(() -> new ApplicationException(ExceptionEnum.CATEGORY_NOT_FOUND));
    }

    @Override
    public void findByName() {
        String name = InputUtil.getInstance().requiredString("Enter the category name: ");
        Category foundCategory = categoryRepository.findByName(name);
        System.out.println(foundCategory);
    }

    @Override
    public void updateCategory() {
        System.out.println(categoryRepository.findAll());
        long id = InputUtil.getInstance().requiredLong("Enter the category ID for update: ");
        Category updatedCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ExceptionEnum.CATEGORY_NOT_FOUND));
        String newName = InputUtil.getInstance().requiredString("Enter the new category name: ");
        updatedCategory.setName(newName);
        String newDescription = InputUtil.getInstance().requiredString("Enter the new category description: ");
        updatedCategory.setDescription(newDescription);
        System.out.println(Constant.UPDATE_SUCCESSFULLY);
    }

    @Override
    public void deleteCategory() {
        System.out.println(categoryRepository.findAll());
        long id = InputUtil.getInstance().requiredLong("Enter the category ID for delete: ");
        Category deletedCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ExceptionEnum.CATEGORY_NOT_FOUND));
        categoryRepository.delete(deletedCategory);
        System.out.println(Constant.DELETE_SUCCESSFULLY);
    }

    @Override
    public void deleteAllCategory() {
        List<Category> allCategories = categoryRepository.findAll();
        if (allCategories.isEmpty()) {
            throw new ApplicationException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        categoryRepository.deleteAll(allCategories);
    }

}
