package com.example.trackware.Controller;

import com.example.trackware.Api.ApiResponse;
import com.example.trackware.Model.Company;
import com.example.trackware.Service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/get")
    public ResponseEntity getAllCompanies() {
        return ResponseEntity.status(200).body(companyService.getAllCompanies());
    }

    @PostMapping("/add")
    public ResponseEntity addCompany(@RequestBody @Valid Company company) {

        companyService.addCompany(company);
        return ResponseEntity.status(200).body(new ApiResponse("Company added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCompany(@PathVariable Integer id,@RequestBody @Valid Company company) {

        companyService.updateCompany(id, company);
        return ResponseEntity.status(200).body(new ApiResponse("Company updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCompany(@PathVariable Integer id) {
        companyService.deleteCompany(id);
        return ResponseEntity.status(200).body(new ApiResponse("Company deleted"));
    }
}
