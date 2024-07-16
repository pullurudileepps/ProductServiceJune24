package com.example.FirstAPIJune24.Dtos;

import com.example.FirstAPIJune24.Model.Product;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductResponseDto implements Serializable {
    private Product product;
}
