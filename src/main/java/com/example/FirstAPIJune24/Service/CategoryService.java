package com.example.FirstAPIJune24.Service;

import com.example.FirstAPIJune24.Model.Category;

public interface CategoryService {
    Category getCategoryById(int id);
    Category createCategory(String categoryName);
    Category updateCategory(int id, String categoryName);
    void deleteCategoryById(int id);
}
