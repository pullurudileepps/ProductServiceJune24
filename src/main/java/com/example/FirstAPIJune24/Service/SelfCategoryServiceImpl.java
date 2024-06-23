package com.example.FirstAPIJune24.Service;

import com.example.FirstAPIJune24.Model.Category;
import com.example.FirstAPIJune24.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("selfCategory")
public class SelfCategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    @Autowired
    public SelfCategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryById(int id) {
        return this.categoryRepository.findCategoriesById(id);
    }

    @Override
    public Category createCategory(String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        return this.categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(int id, String categoryName) {
        Category category = this.categoryRepository.findCategoriesById(id);
        category.setName(categoryName);
        return this.categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(int id) {
        this.categoryRepository.deleteById(id);
    }
}
