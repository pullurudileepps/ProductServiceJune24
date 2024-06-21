package com.example.FirstAPIJune24.representing_inheritance.single_table;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("2")
public class Mentor extends User {
    private String company;
    private double avgRating;
}
