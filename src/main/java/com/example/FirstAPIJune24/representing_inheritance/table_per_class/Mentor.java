package com.example.FirstAPIJune24.representing_inheritance.table_per_class;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "tpc_Mentor")
public class Mentor extends User {
    private String company;
    private double avgRating;
}
