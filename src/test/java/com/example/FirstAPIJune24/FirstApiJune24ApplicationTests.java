package com.example.FirstAPIJune24;

import com.example.FirstAPIJune24.Model.Product;
import com.example.FirstAPIJune24.Projection.ProductInfo;
import com.example.FirstAPIJune24.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class FirstApiJune24ApplicationTests {

	@Autowired
	private ProductRepository productRepository;
	@Test
	void contextLoads() {
	}

	@Test
	public void testFindCategoryDetail() {
		int productId = 1;
		ProductInfo productInfo = productRepository.findCategoryDetail(productId);

		assertNotNull(productInfo);
		System.out.println("ID: " + productInfo.getId());
		System.out.println("Title: " + productInfo.getTitle());
		System.out.println("Description: " + productInfo.getDescription());
	}

}
