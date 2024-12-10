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
public class Branch  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Branch name not valid")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String name;

    @NotEmpty(message = "Location not valid")
    @Column(columnDefinition = "varchar(150) not null")
    private String location;

    @Positive(message = "Company id not valid")
    @Column(columnDefinition = "int not null")
    private Integer company_id;

    @Positive(message = "warehouse id not valid")
    @Column(columnDefinition = "int not null")
    private Integer wareHouse_id;


}
