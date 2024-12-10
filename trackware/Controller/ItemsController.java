package com.example.trackware.Controller;

import com.example.trackware.Api.ApiResponse;
import com.example.trackware.Model.Items;
import com.example.trackware.Service.ItemsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
public class ItemsController {
    private final ItemsService itemsService;

    @GetMapping("/get")
    public ResponseEntity getItems() {
        return ResponseEntity.status(200).body(itemsService.getAllItems());
    }

    @PostMapping("/add")
    public ResponseEntity addItem(@RequestBody @Valid Items item) {
        itemsService.addItem(item);
        return ResponseEntity.status(200).body(new ApiResponse("Item added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateItem(@PathVariable Integer id ,@RequestBody @Valid Items item) {
        itemsService.updateItem(id,item);
        return ResponseEntity.status(200).body(new ApiResponse("Item updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteItem(@PathVariable Integer id) {
        itemsService.deleteItem(id);
        return ResponseEntity.status(200).body(new ApiResponse("Item deleted"));
    }

    @PutMapping("/checkExpiredItems/{item_id}")
    public ResponseEntity checkExpiredItems(@PathVariable Integer item_id){
        itemsService.checkExpiredItems(item_id);
        return ResponseEntity.status(200).body(new ApiResponse("Item expired"));
    }


    @PutMapping("/changeCategoryOfItem/{item_id}/{newCategory_id}")
    public ResponseEntity changeCategoryOfItem(@PathVariable Integer item_id,@PathVariable Integer newCategory_id) {
        itemsService.changeCategoryOfItem(item_id, newCategory_id);
        return ResponseEntity.status(200).body(new ApiResponse("Category Item Changed"));
    }

    @GetMapping("/cheapestPrice/{item_name}")
    public ResponseEntity cheapestPrice(@PathVariable String item_name){
        Items item = itemsService.cheapestPrice(item_name);
        return ResponseEntity.status(200).body(item);
    }


}
