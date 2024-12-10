package com.example.trackware.Controller;

import com.example.trackware.Model.Items;
import com.example.trackware.Model.WareHouse;
import com.example.trackware.Service.WareHouseItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wareHouseItem")
@RequiredArgsConstructor
public class WareHouseItemController {
    private final WareHouseItemService wareHouseItemService;

    @PostMapping("/addItemToWarehouse/{company_id}/{warehouse_id}/{item_id}/{quantity}")
    public ResponseEntity addItemToWarehouse(@PathVariable Integer company_id,@PathVariable Integer warehouse_id,@PathVariable Integer item_id,@PathVariable Integer quantity){
        wareHouseItemService.addItemToWarehouse(company_id,warehouse_id,item_id,quantity);
        return ResponseEntity.status(200).body("Item Ware House added");
    }

    @PutMapping("/transferItems/{company_id}/{SourceWarehouse_id}/{destinationWarehouse_id}/{item_id}/{quantity}")
    public ResponseEntity transferItems(@PathVariable Integer company_id,@PathVariable Integer SourceWarehouse_id,@PathVariable Integer destinationWarehouse_id,@PathVariable Integer item_id,@PathVariable Integer quantity){
        wareHouseItemService.transferItems(company_id,SourceWarehouse_id,destinationWarehouse_id,item_id,quantity);
        return ResponseEntity.status(200).body("Item Ware House transferred");
    }

    @GetMapping("/checkItemsExpiration/{wereHose_id}")
    public ResponseEntity checkItemsExpiration(@PathVariable Integer wereHose_id){
        List<Items> items = wareHouseItemService.checkItemsExpiration(wereHose_id);
        return ResponseEntity.status(200).body(items);
    }

    @GetMapping("/getWareHousesByItem/{item_id}")
    public ResponseEntity getAllWareHousesForItem(@PathVariable Integer item_id){
        List<WareHouse> AllWareHousesForItem = wareHouseItemService.getAllWareHousesForItem(item_id);
        return ResponseEntity.status(200).body(AllWareHousesForItem);
    }

    @PutMapping("/damagedItems/{company_id}/{wareHouse_id}/{item_id}/{quantity}")
    public ResponseEntity damagedItems(@PathVariable Integer company_id,@PathVariable Integer wareHouse_id ,@PathVariable Integer item_id,@PathVariable Integer quantity){
        wareHouseItemService.damagedItems( company_id, wareHouse_id , item_id, quantity);
        return ResponseEntity.status(200).body("Damaged items deleted");
    }

    @GetMapping("/lowQuantity/{company_id}/{wareHouse_id}/{quantity}")
    public ResponseEntity lowQuantity(@PathVariable Integer company_id,@PathVariable Integer wareHouse_id,@PathVariable Integer quantity){
        List<Items> lowQuantityItems = wareHouseItemService.lowQuantity(company_id,wareHouse_id,quantity);
        return ResponseEntity.status(200).body(lowQuantityItems);
    }

    @GetMapping("/get")
    public ResponseEntity getAllWareHouseItem(){
        return ResponseEntity.status(200).body(wareHouseItemService.getAllWareHouseItems());
    }

}
