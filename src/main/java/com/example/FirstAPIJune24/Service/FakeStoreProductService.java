package com.example.FirstAPIJune24.Service;

import com.example.FirstAPIJune24.Config.WebClientConfig;
import com.example.FirstAPIJune24.Dtos.ProductDto;
import com.example.FirstAPIJune24.Dtos.ProductResponseDto;
import com.example.FirstAPIJune24.Model.Category;
import com.example.FirstAPIJune24.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStore")
public class FakeStoreProductService implements ProductService{
    // AppConfig restTemplateConfig;
    WebClientConfig webClientConfig;
//    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public FakeStoreProductService(WebClientConfig webClientConfig) {
        this.webClientConfig = webClientConfig;
    }

    @Override
    public ProductResponseDto getProductById(int id) {
        /* ProductDto productDto = this.restTemplateConfig.restTemplate().getForObject("https://fakestoreapi.com/products/" + id, ProductDto.class);*/
//        ProductResponseDto productObj = (ProductResponseDto) this.redisTemplate.opsForHash().get("PRODUCTS", "products_" + id);
//        if(productObj != null){
//            return productObj;
//        }
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
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setProduct(product);
//        this.redisTemplate.opsForHash().put("PRODUCTS", "products_" + id, productResponseDto);
        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        int idx = 0;
        String url = "https://fakestoreapi.com/products/";

        //ResponseEntity<List<ProductDto>> response = this.restTemplateConfig.restTemplate().exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductDto>>(){});
        //List<ProductDto> productDtoList = response.getBody()

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

    @Override
    public Product createProduct(String title, String description, double price, String image, String categoryName) {
        return null;
    }

    @Override
    public Product UpdatePrice(int id, double price) {
        return null;
    }

    @Override
    public Product UpdateImage(int id, String image) {
        return null;
    }

    @Override
    public void deleteProduct(int id) {

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

    @Override
    public Page<Product> pagination(int pageSize, int pageNumber) {
        return null;
    }

    @Override
    public Page<Product> paginationSort(int pageSize, int pageNumber, String sortBy) throws IllegalArgumentException {
        return null;
    }
}
