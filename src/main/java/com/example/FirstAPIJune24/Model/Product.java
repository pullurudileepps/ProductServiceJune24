package com.example.FirstAPIJune24.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity(name = "products")
public class Product extends BaseModel implements Serializable {
    private String title;
    private double price;
    private String description;
    private String image;
    @ManyToOne
    private Category category;
    private int qty;
}
