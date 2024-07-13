package com.example.FirstAPIJune24.Repository;

import com.example.FirstAPIJune24.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoriesById(int id);
    void deleteCategoryById(int id);
    Optional<Category> findCategoriesByName(String name);
}
