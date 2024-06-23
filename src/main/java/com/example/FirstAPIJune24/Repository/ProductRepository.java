package com.example.FirstAPIJune24.Repository;

import com.example.FirstAPIJune24.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductById(int id);
    @Query("DELETE FROM Product p WHERE p.id = :id")
    void deleteProductById(@Param("id") int id);
}
