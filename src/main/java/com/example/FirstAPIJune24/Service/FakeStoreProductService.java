package com.example.FirstAPIJune24.Service;

import com.example.FirstAPIJune24.Config.AppConfig;
import com.example.FirstAPIJune24.Config.WebClientConfig;
import com.example.FirstAPIJune24.Dtos.ProductDto;
import com.example.FirstAPIJune24.Dtos.ProductResponseDto;
import com.example.FirstAPIJune24.Model.Category;
import com.example.FirstAPIJune24.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{
//    AppConfig restTemplateConfig;
    WebClientConfig webClientConfig;

    @Autowired
    public FakeStoreProductService(WebClientConfig webClientConfig) {
        this.webClientConfig = webClientConfig;
    }

    @Override
    public ProductResponseDto getProductById(int id) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
//        ProductDto productDto = this.restTemplateConfig.restTemplate().getForObject("https://fakestoreapi.com/products/" + id, ProductDto.class);
        ProductDto productDto = webClientConfig.webClientBuilder()
                .build()
                .get()
                .uri("https://fakestoreapi.com/products/" + id)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
        Product product = null;
        if (!ObjectUtils.isEmpty(productDto)) {
            int idx = 0;
            product = convertProductDtoToProduct(productDto, ++idx);
        }
        productResponseDto.setProduct(product);
        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        int idx = 0;
        String url = "https://fakestoreapi.com/products/";

//        ResponseEntity<List<ProductDto>> response = this.restTemplateConfig.restTemplate().exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductDto>>(){});
//        List<ProductDto> productDtoList = response.getBody()

        List<ProductDto> productDtoList = webClientConfig.webClientBuilder()
                .build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ProductDto>>() {
                })
                .block();
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(productDtoList)) {
            for (ProductDto productDto : productDtoList) {
                Product product = convertProductDtoToProduct(productDto, ++idx);
                ProductResponseDto productResponseDto = new ProductResponseDto();
                productResponseDto.setProduct(product);
                productResponseDtoList.add(productResponseDto);
            }
        }
        return productResponseDtoList;
    }
    private Product convertProductDtoToProduct(ProductDto productDto, int i) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        Category category = new Category();
        category.setId(i);
        category.setName(productDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
