package com.example.trackware.Controller;

import com.example.trackware.Api.ApiResponse;
import com.example.trackware.Model.CompanyWareHouse;
import com.example.trackware.Service.CompanyWareHouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/companyWareHouse")
@RequiredArgsConstructor
public class CompanyWareHouseController {
    private final CompanyWareHouseService companyWareHouseService;

    @GetMapping("/get")
    public ResponseEntity getAllCompanyWareHouses() {
        return ResponseEntity.status(200).body(companyWareHouseService.getAllCompanyWareHouse());
    }

    @PostMapping("/add")
    public ResponseEntity addCompanyWareHouse(@RequestBody @Valid CompanyWareHouse companyWareHouse) {
        companyWareHouseService.addCompanyWareHouse(companyWareHouse);
        return ResponseEntity.status(200).body("Company Ware House added");
    }



    @PutMapping("/changeOwnership/{wareHouse_id}/{oldCompany_id}/{newCompany_id}")
    public ResponseEntity changeOwnership(@PathVariable Integer wareHouse_id, @PathVariable Integer oldCompany_id, @PathVariable Integer newCompany_id){
        companyWareHouseService.changeOwnership(wareHouse_id,oldCompany_id, newCompany_id);
        return ResponseEntity.status(200).body(new ApiResponse("Company WareHouse Ownership changed"));
    }
}
