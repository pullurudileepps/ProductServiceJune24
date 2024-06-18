package com.example.FirstAPIJune24.Dtos;

import lombok.Data;

@Data
public class ProductDto {
    private long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
}
