package com.example.trackware.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Employee name not valid")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @NotEmpty(message = "Role not valid")
    @Column(columnDefinition = "varchar(50) not null")
    private String role;

    @NotEmpty(message = "password not valid")
    @Column(columnDefinition = "varchar(25)")
    private String password;

    @NotEmpty(message = "Email not valid")
    @Email(message = "Invalid email format")
    @Column(columnDefinition = "varchar(30) not null")
    private String email;

    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with 05 and be 10 digits long")
    @NotEmpty(message = "Phone number not valid")
    @Column(columnDefinition = "varchar(10) not null")
    private String phone;


    @Column(columnDefinition = "int ")
    private Integer branch_id;


    @Column(columnDefinition = "int ")
    private Integer wareHouse_id;


}
