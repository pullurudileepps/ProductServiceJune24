package com.example.FirstAPIJune24.representing_inheritance.single_table;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("1")
public class Learner extends User {
    private String college;
    private String company;
}
