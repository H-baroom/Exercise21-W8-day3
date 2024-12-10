package com.example.trackware.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class WareHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "Warehouse name not valid")
    @Column(columnDefinition = "varchar(100) not null", unique = true)
    private String name;

    @NotEmpty(message = "Location not valid")
    @Column(columnDefinition = "varchar(150) not null")
    private String location;

    @Positive(message = "price not valid")
    @Column(columnDefinition = "int not null")
    private Integer price;

    @Positive(message = "Minimum Area not valid")
    @Column(columnDefinition = "int not null" )
    private Integer minArea;

    @Positive(message = "Maximum Area not valid")
    @Column(columnDefinition = "int not null" )
    private Integer maxArea;

    @Column(columnDefinition = "boolean default false")
    private Boolean isBooked = false;
}
