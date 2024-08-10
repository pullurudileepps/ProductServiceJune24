package com.example.FirstAPIJune24.Service;

import com.example.FirstAPIJune24.Dtos.ProductResponseDto;
import com.example.FirstAPIJune24.Model.Category;
import com.example.FirstAPIJune24.Model.Product;
import com.example.FirstAPIJune24.Projection.ProductInfo;
import com.example.FirstAPIJune24.Repository.ProductRepository;
import com.example.FirstAPIJune24.exceptions.ProductNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        /*
        Filter columns from the table using Projection (SQL/HQL Query)
        Optional<ProductInfo> productInfo = this.productRepository.filterProductById(id);
        if(productInfo.isPresent()){
            System.out.println("data persist");
            ProductInfo prod = productInfo.get();

            System.out.println("SelfProductServiceImpl{" +
                    "id=" + prod.getId() +
                    ", title=" + prod.getTitle() +
                    ". description=" + prod.getDescription() +
                    '}');

        }*/
        responseDto.setProduct(product);
        return responseDto;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(String title, String description, double price, String image,String categoryName, int availableQuantity) {
        Category category = this.categoryService.createCategory(categoryName);
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImage(image);
        product.setCategory(category);
        product.setQty(availableQuantity);
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
        int categoryId = (int) product.getCategory().getId();
        productRepository.deleteById(productId);
        categoryService.deleteCategoryById(categoryId);
    }

    @Override
    public Page<Product> pagination(int pageSize, int pageNumber) {
        return this.productRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<Product> paginationSort(int pageSize, int pageNumber, String sortBy) throws IllegalArgumentException {
        if (pageSize <= 0 || pageNumber < 0) {
            throw new IllegalArgumentException("Invalid page size or page number.");
        }
        String[] split = sortBy.split(",");
        List<Sort.Order> orders = new ArrayList<>();

        if (split.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid sortBy format. It should be in 'field,order' pairs.");
        }
        try {
            for (int i = 0; i < split.length; i += 2) {
                String property = split[i].trim();
                String direction = split[i + 1].trim();

                Sort.Direction sortDirection = direction.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
                orders.add(new Sort.Order(sortDirection, property));
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid sortBy format. It should be in 'field,order' pairs.");
        }
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(orders)));
    }

    @Override
    public List<Product> getProductsById(List<Integer> productIds) throws ProductNotFoundException {
        Optional<List<Product>> productOptional = this.productRepository.findAllByIdIn(productIds);
        return productOptional.orElseGet(ArrayList::new);

    }

    @Override
    public Product updateAvailableQuantity(int productId, int updatedQuantity) throws ProductNotFoundException {
        ProductResponseDto productById = getProductById(productId);
        Product product = productById.getProduct();
        product.setQty(updatedQuantity);
        return this.productRepository.save(product);
    }
}
