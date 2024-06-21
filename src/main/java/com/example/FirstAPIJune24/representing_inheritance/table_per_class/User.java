package com.example.FirstAPIJune24.representing_inheritance.table_per_class;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tpc_User")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    private long id;
    private String name;
    private String email;
    private String password;
}
