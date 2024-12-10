package com.example.trackware.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "User name not valid")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "Email not valid")
    @Email(message = "Invalid email format")
    @Column(columnDefinition = "varchar(30) not null", unique = true)
    private String email;

}
