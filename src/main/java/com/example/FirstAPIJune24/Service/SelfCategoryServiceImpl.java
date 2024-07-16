package com.example.FirstAPIJune24.Service;

import com.example.FirstAPIJune24.Model.Category;
import com.example.FirstAPIJune24.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("selfCategory")
public class SelfCategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    @Autowired
    public SelfCategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryById(int id) {
        Optional<Category> categoryOptional = this.categoryRepository.findCategoriesById(id);
        return categoryOptional.orElseGet(Category::new);
    }

    @Override
    public Category createCategory(String categoryName) {
        Optional<Category> existingCategory = this.categoryRepository.findCategoriesByName(categoryName);
        if (existingCategory.isPresent()) {
            return existingCategory.get();
        }
        Category category = new Category();
        category.setName(categoryName);
        return this.categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(int id, String categoryName) {
        Optional<Category> categoryOptional = this.categoryRepository.findCategoriesById(id);
        Category category = categoryOptional.get();
        category.setName(categoryName);
        return this.categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(int id) {
        this.categoryRepository.deleteById(id);
    }
}
