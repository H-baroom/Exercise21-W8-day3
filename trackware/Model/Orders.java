package com.example.trackware.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Positive(message = "quantity not valid ")
    @Column(columnDefinition = "int not null")
    private Integer quantity;

    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(columnDefinition = "datetime")
    private LocalDateTime updatedAt;

    @Column(columnDefinition = "varchar(150)")
    private String notes;

    @Column(columnDefinition = "varchar(20)")
    private String status;


    @Column(columnDefinition = "int")
    private Integer totalPrice;

    @Positive(message = "Branch id not valid ")
    @Column(columnDefinition = "int not null")
    private Integer branch_id;

    @Positive(message = "Warehouse id not valid ")
    @Column(columnDefinition = "int not null")
    private Integer warehouse_id;

    @Positive(message = "Item id not valid ")
    @Column(columnDefinition = "int not null")
    private Integer item_id;
}
