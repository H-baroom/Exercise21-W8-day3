package com.example.trackware.Controller;

import com.example.trackware.Api.ApiResponse;
import com.example.trackware.Model.Branch;
import com.example.trackware.Model.Orders;
import com.example.trackware.Service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/branch")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService branchService;

    @GetMapping("/get")
    public ResponseEntity getAllBranches() {
        return ResponseEntity.status(200).body(branchService.getAllBranch());
    }
    @PostMapping("/add")
    public ResponseEntity addBranch(@RequestBody @Valid Branch branch) {

        branchService.addBranch(branch);
        return ResponseEntity.status(200).body(new ApiResponse("Branch added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateBranch(@PathVariable Integer id,@RequestBody @Valid Branch branch) {

        branchService.updateBranch(id, branch);
        return ResponseEntity.status(200).body(new ApiResponse("Branch updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBranch(@PathVariable Integer id) {
        branchService.deleteBranch(id);
        return ResponseEntity.status(200).body(new ApiResponse("Branch deleted"));
    }

    @GetMapping("/getOrderHistory/{branch_id}")
    public ResponseEntity getOrderHistory(@PathVariable Integer branch_id) {
        List<Orders> orderHistory = branchService.getOrderHistory(branch_id);
        return ResponseEntity.status(200).body(orderHistory);
    }

    @GetMapping("/getAllBranchesForOneCompany/{company_id}")
    public ResponseEntity getAllBranchesForOneCompany(@PathVariable Integer company_id) {
        List<Branch> getAllBranchesForOneCompany = branchService.getAllBranchesForOneCompany(company_id);
        return ResponseEntity.status(200).body(getAllBranchesForOneCompany);
    }
}
