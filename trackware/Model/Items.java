package com.example.trackware.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "Item name not valid")
    @Column(columnDefinition = "varchar(100) not null")
    private String name;


    @Positive(message = "Price not valid")
    @Column(columnDefinition = "int not null")
    private Integer price;

    public Items(Integer price) {
        this.price = price;
    }

    @Positive(message = "Quantity not valid")
    @Column(columnDefinition = "int not null")
    private Integer quantity;

    @Future(message = "Expiration date must be in the future")
    @Column(columnDefinition = "timestamp not null")
    private LocalDateTime expiration_date;


    @Column(columnDefinition = "boolean default false")
    private Boolean isExpired = false ;


    @Positive(message = "Category Id not valid")
    @Column(columnDefinition = "int not null")
    private Integer category_id;
}
