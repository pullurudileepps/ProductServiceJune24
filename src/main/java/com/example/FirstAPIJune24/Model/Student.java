package com.example.FirstAPIJune24.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


/* Many to Many relationship */
@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany
    private List<Course> courses;
}
