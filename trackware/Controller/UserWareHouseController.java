package com.example.trackware.Controller;

import com.example.trackware.Model.UserWareHouse;
import com.example.trackware.Service.UserService;
import com.example.trackware.Service.UserWareHouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/userWareHouse")
@RequiredArgsConstructor
public class UserWareHouseController {
    private final UserWareHouseService userWareHouseService;

    @GetMapping("/get")
    public ResponseEntity getAllUserWareHouse() {
        return ResponseEntity.status(200).body(userWareHouseService.getAllUserWareHouses());
    }

    @PostMapping("/add")
    public ResponseEntity addUserWareHouse(@RequestBody @Valid UserWareHouse userWareHouse) {
        userWareHouseService.addUserWareHouse(userWareHouse);
        return ResponseEntity.status(201).body("User Warehouse added successfully");
    }
}
