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

    Optional<List<Product>> findAllByIdIn(List<Integer> ids);

    @Query(value = "SELECT p.id as id, p.description as description, p.title as title FROM products p WHERE p.id = :id", nativeQuery = true)
    Optional<ProductInfo> filterProductById(@Param("id") int id);
}
