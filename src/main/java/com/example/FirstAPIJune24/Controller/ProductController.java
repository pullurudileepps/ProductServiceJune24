package com.example.FirstAPIJune24.Controller;

import com.example.FirstAPIJune24.Component.AuthUtils;
import com.example.FirstAPIJune24.Dtos.*;
import com.example.FirstAPIJune24.Model.Product;
import com.example.FirstAPIJune24.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        Product product = this.productService.createProduct(requestDto.getTitle(), requestDto.getDescription(), requestDto.getPrice(), requestDto.getImage(), requestDto.getCategoryName(),requestDto.getAvailableQuantity());
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
    @GetMapping("/pagination")
    public ResponseEntity<Page<Product>> pagination(@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") int pageNum){

        Page<Product> products = productService.pagination(pageSize, pageNum);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/pagination-sort")
    public ResponseEntity<Page<Product>> paginationSort(@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                        @RequestParam("sortBy") String sortBy) {
        try {
            Page<Product> sortedProduct = productService.paginationSort(pageSize, pageNum, sortBy);
            return new ResponseEntity<>(sortedProduct, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/{id}/available_quantity")
    public UpdateQuantityResponseDTO updateAvailableQuantity(@PathVariable("id") int productId, @RequestBody UpdateQuantityRequestDTO requestDTO) {
        int quantity = requestDTO.getQuantity();
        UpdateQuantityResponseDTO responseDTO = new UpdateQuantityResponseDTO();
        try {
            Product product = this.productService.updateAvailableQuantity(productId, quantity);
            responseDTO.setProduct(product);
            responseDTO.setMessage("Quantity of product with ID: " + productId + ", has been UPDATED");
        } catch (Exception e) {
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }
    @PostMapping("/details")
    public List<Product> getProductsById(@RequestBody GetProductsByIdRequestDTO requestDTO) {
        List<Integer> productIds = requestDTO.getProductIds();
        System.out.println(productIds);
        List<Product> products = new ArrayList<>();
        try {
            products = this.productService.getProductsById(productIds);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

}