package com.example.trackware.Service;

import com.example.trackware.Api.ApiException;
import com.example.trackware.Model.*;
import com.example.trackware.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final WareHouseRepository wareHouseRepository;
    private final CompanyRepository companyRepository;

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public void addEmployee(Employee employee){
        if (employee.getBranch_id() == null ^ employee.getWareHouse_id() == null){
            if (branchRepository.findBranchById(employee.getBranch_id()) != null || wareHouseRepository.findWareHouseById(employee.getWareHouse_id()) != null){
                employeeRepository.save(employee);
            }
            throw new ApiException("Branch or warehouse not found");
        }
        if (employee.getBranch_id() != null || employee.getWareHouse_id() != null){
            throw new ApiException("Employee should be work in Branch only or work in Warehouse only");
        }
        throw new ApiException("Employee should be work in Branch or Warehouse");
    }

    public void updateEmployee(Integer id,Employee employee){
        Employee updateEmployee = employeeRepository.findEmployeeById(id);
        if(updateEmployee == null){
            throw new ApiException("Employee not found");
        }
        if (employee.getBranch_id() == null ^ employee.getWareHouse_id() == null){
            if (branchRepository.findBranchById(employee.getBranch_id()) != null ^ wareHouseRepository.findWareHouseById(employee.getWareHouse_id()) != null){
                updateEmployee.setName(employee.getName());
                updateEmployee.setPassword(employee.getPassword());
                updateEmployee.setEmail(employee.getEmail());
                updateEmployee.setPhone(employee.getPhone());
                updateEmployee.setRole(employee.getRole());
                updateEmployee.setBranch_id(employee.getBranch_id());
                updateEmployee.setWareHouse_id(employee.getWareHouse_id());
                employeeRepository.save(updateEmployee);
            }
            throw new ApiException("Branch or warehouse not found");
        }
        if (employee.getBranch_id() == null && employee.getWareHouse_id() == null){
            throw new ApiException("Employee should be work in Branch only or work in Warehouse only");
        }
        throw new ApiException("Employee should be work in Branch or Warehouse");

    }

    public void deleteEmployee(Integer id){
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
        }
        throw new ApiException("Employee not found");
    }

    public void transferEmployee(Integer company_id, Integer employee_id,Integer branch_id, Integer warehouse_id){
        Employee employee = employeeRepository.findEmployeeById(employee_id);
        Company company = companyRepository.findCompanyById(company_id);
        if (employee == null){
            throw new ApiException("Employee not found");
        }
        if (company == null){
            throw new ApiException("company not found");
        }
        Branch branch = branchRepository.findBranchByIdAndCompanyIdAndwareHouseId(branch_id,company_id,warehouse_id);
        WareHouse wareHouse = wareHouseRepository.findWareHouseById(employee.getWareHouse_id());
        if (branch.getId() == null && wareHouse.getId() != null){
                Branch branch1 = branchRepository.findBranchById(branch_id);
                if (branch1 == null){
                    throw new ApiException("Branch not found");
                }
                if (branch1.getCompany_id() == company_id){
                    employee.setBranch_id(branch_id);
                    employee.setWareHouse_id(null);
                    employeeRepository.save(employee);
                }
        }
        if (branch.getId() != null && wareHouse.getId() == null){
            if (branch.getCompany_id() == company_id){
                employee.setBranch_id(null);
                employee.setWareHouse_id(warehouse_id);
                employeeRepository.save(employee);
            }
        }


    }
}
