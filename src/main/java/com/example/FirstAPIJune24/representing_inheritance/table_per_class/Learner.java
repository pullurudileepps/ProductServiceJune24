package com.example.FirstAPIJune24.representing_inheritance.table_per_class;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "tpc_Learner")
public class Learner extends User {
    private String college;
    private String company;
}
