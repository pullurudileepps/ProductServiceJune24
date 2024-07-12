package com.example.FirstAPIJune24.Controller;

import com.example.FirstAPIJune24.Dtos.CategoryRequestDto;
import com.example.FirstAPIJune24.Model.Category;
import com.example.FirstAPIJune24.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;
    @Autowired
    public CategoryController(@Qualifier("selfCategory") CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return this.categoryService.getCategoryById(id);
    }

    @PostMapping("/createCategory")
    public Category createCategory(@RequestBody CategoryRequestDto requestDto) {
        return this.categoryService.createCategory(requestDto.getCategoryName());
    }

    @PutMapping("/updateCategory")
    public Category updateCategory(@RequestBody Category requestDto) {
        return this.categoryService.updateCategory(requestDto.getId(), requestDto.getName());
    }

    @DeleteMapping("/{id}")
    public boolean deleteCategory(@PathVariable int id) {
        try {
            this.categoryService.deleteCategoryById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
