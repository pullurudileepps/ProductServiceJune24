package com.example.FirstAPIJune24.Service;

import com.example.FirstAPIJune24.Dtos.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto getProductById(int id);
    List<ProductResponseDto> getAllProducts();
}
