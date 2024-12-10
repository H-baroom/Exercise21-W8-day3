package com.example.trackware.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UserWareHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Positive(message = "Area not valid")
    @Column(columnDefinition = "int not null" )
    private Integer Area;

    @Positive(message = "User id not valid ")
    @Column(columnDefinition = "int not null")
    private Integer user_id;



    @Column(columnDefinition = "int")
    private Integer wareHouse_id;
}