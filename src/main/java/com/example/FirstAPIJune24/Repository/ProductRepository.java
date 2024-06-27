package com.example.FirstAPIJune24.Repository;

import com.example.FirstAPIJune24.Model.Product;
import com.example.FirstAPIJune24.Projection.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findProductById(int id);

    @Query("select p.id as id, p.title as title, p.description from Product p where p.id = :id")
    ProductInfo findCategoryDetail(@Param("id") int id);
}
