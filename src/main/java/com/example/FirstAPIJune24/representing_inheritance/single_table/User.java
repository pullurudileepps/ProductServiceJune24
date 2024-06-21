package com.example.FirstAPIJune24.representing_inheritance.single_table;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity(name = "st_User")
@DiscriminatorColumn(
        name = "user_type",
        discriminatorType = DiscriminatorType.INTEGER
)
@DiscriminatorValue("0")
public class User {
    @Id
    private long id;
    private String name;
    private String email;
    private String password;
}
