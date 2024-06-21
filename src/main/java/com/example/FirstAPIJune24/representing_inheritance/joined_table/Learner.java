package com.example.FirstAPIJune24.representing_inheritance.joined_table;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity(name = "jt_Learner")
@PrimaryKeyJoinColumn(name = "user_id")
public class Learner extends User {
    private String college;
    private String company;
}
