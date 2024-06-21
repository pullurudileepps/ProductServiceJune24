package com.example.FirstAPIJune24.representing_inheritance.mapped_super_class;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "msc_TA")
public class TA extends User {
    private double avgRating;
    private String college;
}
