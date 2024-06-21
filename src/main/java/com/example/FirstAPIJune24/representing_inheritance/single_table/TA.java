package com.example.FirstAPIJune24.representing_inheritance.single_table;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("3")
public class TA extends User {
    private double avgRating;
    private String college;
}
