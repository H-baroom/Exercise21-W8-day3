package com.example.trackware.Controller;

import com.example.trackware.Api.ApiResponse;
import com.example.trackware.Model.Employee;
import com.example.trackware.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/get")
    public ResponseEntity getAllEmployees() {
        return ResponseEntity.status(200).body(employeeService.getAllEmployees());
    }

    @PostMapping("/add")
    public ResponseEntity addEmployee(@RequestBody @Valid Employee employee) {

        employeeService.addEmployee(employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEmployee(@PathVariable Integer id ,@RequestBody @Valid Employee employee) {

        employeeService.updateEmployee(id,employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(200).body(new ApiResponse("Employee deleted"));
    }

    @PutMapping("/transferEmployee/{company_id}/{employee_id}/{branch_id}/{warehouse_id}")
    public ResponseEntity transferEmployee(@PathVariable Integer company_id,@PathVariable Integer employee_id,@PathVariable Integer branch_id,@PathVariable Integer warehouse_id){
        employeeService.transferEmployee(company_id,employee_id,branch_id,warehouse_id);
        return ResponseEntity.status(200).body(new ApiResponse("Employee transferred"));
    }

}
