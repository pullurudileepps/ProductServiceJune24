package com.example.FirstAPIJune24.representing_inheritance.table_per_class;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "tpc_TA")
public class TA extends User {
    private double avgRating;
    private String college;
}
