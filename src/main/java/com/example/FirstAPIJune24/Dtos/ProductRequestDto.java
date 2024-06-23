package com.example.FirstAPIJune24.Dtos;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String title;
    private double price;
    private String description;
    private String image;
    private String categoryName;
}
