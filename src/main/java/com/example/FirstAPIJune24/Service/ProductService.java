package com.example.FirstAPIJune24.Service;

import com.example.FirstAPIJune24.Dtos.ProductResponseDto;
import com.example.FirstAPIJune24.Model.Product;
import com.example.FirstAPIJune24.exceptions.ProductNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    ProductResponseDto getProductById(int id);
    List<ProductResponseDto> getAllProducts();
    Product createProduct(String title, String description, double price, String image, String categoryName, int availableQuantity);
    Product UpdatePrice(int id, double price);
    Product UpdateImage(int id, String image);
    void deleteProduct(int id);
    Page<Product> pagination(int pageSize, int pageNumber);
    Page<Product> paginationSort(int pageSize, int pageNumber, String sortBy) throws IllegalArgumentException;
    List<Product> getProductsById(List<Integer> productIds) throws ProductNotFoundException;
    Product updateAvailableQuantity(int productId, int updatedQuantity) throws ProductNotFoundException;
}
