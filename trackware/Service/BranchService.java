package com.example.trackware.Service;

import com.example.trackware.Api.ApiException;
import com.example.trackware.Model.*;
import com.example.trackware.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {
    private final BranchRepository branchRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final WareHouseRepository wareHouseRepository;
    private final CompanyRepository companyRepository;

    public List<Branch> getAllBranch(){
        return branchRepository.findAll();
    }

    public void addBranch(Branch branch){
        Company company = companyRepository.findCompanyById(branch.getCompany_id());
        WareHouse wareHouse = wareHouseRepository.findWareHouseById(branch.getWareHouse_id());
        if (company == null) {
            throw new ApiException("Company not found");
        }
        if (wareHouse == null) {
            throw new ApiException("Warehouse not found");
        }
        branchRepository.save(branch);
    }

    public void updateBranch(Integer id,Branch branch){
        Branch updateBranch = branchRepository.findBranchById(id);
        if (updateBranch == null) {
            throw new ApiException("Branch not found");
        }
        updateBranch.setName(branch.getName());
        updateBranch.setLocation(branch.getLocation());
        updateBranch.setCompany_id(branch.getCompany_id());
        updateBranch.setWareHouse_id(branch.getWareHouse_id());
        branchRepository.save(updateBranch);
    }

    public void deleteBranch(Integer id){
        Branch branch = branchRepository.findBranchById(id);
        if (branch == null) {
            throw new ApiException("Branch not found");
        }
        branchRepository.delete(branch);
    }

    public List<Orders> getOrderHistory(Integer branch_id) {
        Branch BranchOrderHistory = branchRepository.findBranchById(branch_id);
        if (BranchOrderHistory == null) {
            throw new ApiException("Branch not found");
        }

        List<Orders> OrderHistory = orderRepository.findOrderByBranch_id(branch_id);
        return OrderHistory;
    }
    public List<Branch> getAllBranchesForOneCompany(Integer company_id){
        Company company = companyRepository.findCompanyById(company_id);
        if (company == null) {
            throw new ApiException("Company not found");
        }
        List<Branch> allBranchByCompanyId = branchRepository.findAllByCompanyId(company_id);
        return allBranchByCompanyId;
    }
}
