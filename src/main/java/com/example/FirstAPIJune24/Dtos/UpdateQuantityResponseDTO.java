package com.example.FirstAPIJune24.Dtos;

import com.example.FirstAPIJune24.Model.Product;
import lombok.Data;

@Data
public class UpdateQuantityResponseDTO {
    private Product product;
    private String message;
}
