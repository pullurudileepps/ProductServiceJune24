package com.example.FirstAPIJune24.Cardinalities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Wife {
    @Id
    private int id;
    private String name;
    @OneToOne
    private Husband husband;
}
