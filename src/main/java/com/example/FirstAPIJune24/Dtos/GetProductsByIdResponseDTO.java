package com.example.FirstAPIJune24.Dtos;

import com.example.FirstAPIJune24.Model.Product;
import lombok.Data;

import java.util.List;

@Data
public class GetProductsByIdResponseDTO {
    private List<Product> products;
}
