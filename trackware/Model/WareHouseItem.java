package com.example.trackware.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class WareHouseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Positive(message = "Quantity not valid")
    @Column(columnDefinition = "int not null")
    private Integer quantity;

    @Positive(message = "Warehouse id not valid")
    @Column(columnDefinition = "int not null" )
    private Integer wareHouse_id;

    @Positive(message = "item id not valid")
    @Column(columnDefinition = "int not null" )
    private Integer item_id;

    public WareHouseItem(Integer wareHouse_id, Integer item_id, Integer quantity) {
        this.wareHouse_id = wareHouse_id;
        this.item_id = item_id;
        this.quantity = quantity;
    }
}
