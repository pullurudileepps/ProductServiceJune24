package com.example.FirstAPIJune24.Dtos;

import lombok.Data;

import java.util.List;

@Data
public class GetProductsByIdRequestDTO {
    private List<Integer> productIds;
}
