package com.example.FirstAPIJune24.Controller;

import com.example.FirstAPIJune24.Dtos.*;
import com.example.FirstAPIJune24.Model.Product;
import com.example.FirstAPIJune24.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(@Qualifier("selfProduct") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable("id") int id) {
        return this.productService.getProductById(id);
    }

    @GetMapping("/")
    public List<ProductResponseDto> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @PostMapping("/createProduct")
    public Product createProduct(@RequestBody ProductRequestDto product) {
        return this.productService.createProduct(product.getTitle(), product.getDescription(), product.getPrice(), product.getImage(), product.getCategoryName());
    }

    @PatchMapping("/updatePrice")
    public Product updatePrice(@RequestBody ProductPriceRequest request) {
        return this.productService.UpdatePrice(request.getId(), request.getPrice());
    }

    @PatchMapping("/updateImage")
    public Product updateImage(@RequestBody ProductImageRequest request) {
        return this.productService.UpdateImage(request.getId(), request.getImageUrl());
    }

    @DeleteMapping("/{id}")
    public boolean deleteProduct(@PathVariable(name = "id") int id) {
        try {
            this.productService.deleteProduct(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}