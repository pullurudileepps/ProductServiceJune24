package com.example.FirstAPIJune24.Controller;

import com.example.FirstAPIJune24.Component.AuthUtils;
import com.example.FirstAPIJune24.Dtos.*;
import com.example.FirstAPIJune24.Model.Product;
import com.example.FirstAPIJune24.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;
    AuthUtils authUtils;

    @Autowired
    public ProductController(@Qualifier("selfProduct") ProductService productService, AuthUtils authUtils) {
        this.productService = productService;
        this.authUtils = authUtils;
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
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequestDto requestDto, @RequestHeader("Auth") String token) {
        // validate the data
        if(!authUtils.validateToken(token)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Product product = this.productService.createProduct(requestDto.getTitle(), requestDto.getDescription(), requestDto.getPrice(), requestDto.getImage(), requestDto.getCategoryName());
        return new ResponseEntity<>(product, HttpStatus.CREATED);
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