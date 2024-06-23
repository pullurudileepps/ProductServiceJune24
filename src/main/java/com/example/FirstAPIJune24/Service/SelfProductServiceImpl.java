package com.example.FirstAPIJune24.Service;

import com.example.FirstAPIJune24.Dtos.ProductResponseDto;
import com.example.FirstAPIJune24.Model.Category;
import com.example.FirstAPIJune24.Model.Product;
import com.example.FirstAPIJune24.Repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProduct")
public class SelfProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    CategoryService categoryService;

    @Autowired
    public SelfProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public ProductResponseDto getProductById(int id) {
        ProductResponseDto responseDto = new ProductResponseDto();
        Product product = this.productRepository.findProductById(id);
        responseDto.setProduct(product);
        return responseDto;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(String title, String description, double price, String image,String categoryName) {
        Category category = this.categoryService.createCategory(categoryName);
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImage(image);
        product.setCategory(category);
        return this.productRepository.save(product);
    }

    @Override
    public Product UpdatePrice(int id, double price) {
        Product product = this.productRepository.findProductById(id);
        product.setPrice(price);
        return this.productRepository.save(product);
    }

    @Override
    public Product UpdateImage(int id, String image) {
        Product product = this.productRepository.findProductById(id);
        product.setImage(image);
        return this.productRepository.save(product);
    }

    @Override
    public void deleteProduct(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        int categoryId = product.getCategory().getId();
        productRepository.deleteById(productId);
        categoryService.deleteCategoryById(categoryId);
    }
}
