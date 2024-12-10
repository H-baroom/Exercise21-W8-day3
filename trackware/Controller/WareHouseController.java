package com.example.trackware.Controller;

import com.example.trackware.Api.ApiResponse;
import com.example.trackware.Model.WareHouse;
import com.example.trackware.Service.WareHouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wareHouse")
@RequiredArgsConstructor
public class WareHouseController {
    private final WareHouseService wareHouseService;

    @GetMapping("/get")
    public ResponseEntity getAllWareHouses() {
        return ResponseEntity.status(200).body(wareHouseService.getAllWareHouses());
    }

    @PostMapping("/add")
    public ResponseEntity addWareHouse(@RequestBody @Valid WareHouse wareHouse) {
        wareHouseService.addWareHouse(wareHouse);
        return ResponseEntity.status(200).body(new ApiResponse("WareHouse added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateWareHouse(@PathVariable Integer id ,@RequestBody @Valid WareHouse wareHouse) {
        wareHouseService.updateWareHouse(id, wareHouse);
        return ResponseEntity.status(200).body(new ApiResponse("WareHouse updated"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteWareHouse(@PathVariable Integer id) {
        wareHouseService.deleteWareHouse(id);
        return ResponseEntity.status(200).body(new ApiResponse("WareHouse deleted"));
    }

}
