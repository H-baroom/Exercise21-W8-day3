package com.example.trackware.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name not valid")
    @Column(columnDefinition = "varchar(25) not null")
    private String name;

    @NotEmpty(message = "Email not valid")
    @Email(message = "Invalid email format")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;

}
