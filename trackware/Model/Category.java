package com.example.trackware.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Category name not valid")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String name;

    @NotEmpty(message = "Description not valid")
    @Column(columnDefinition = "varchar(270)")
    private String description;




}
