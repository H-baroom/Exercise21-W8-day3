package com.example.trackware.Service;

import com.example.trackware.Api.ApiException;
import com.example.trackware.Model.Branch;
import com.example.trackware.Model.Company;
import com.example.trackware.Model.CompanyWareHouse;
import com.example.trackware.Model.WareHouse;
import com.example.trackware.Repository.BranchRepository;
import com.example.trackware.Repository.CompanyRepository;
import com.example.trackware.Repository.CompanyWareHouseRepository;
import com.example.trackware.Repository.WareHouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyWareHouseService {
    private final CompanyWareHouseRepository companyWareHouseRepository;
    private final CompanyRepository companyRepository;
    private final WareHouseRepository wareHouseRepository;
    private final BranchRepository branchRepository;


    public List<CompanyWareHouse> getAllCompanyWareHouse(){
        return companyWareHouseRepository.findAll();
    }

    public void addCompanyWareHouse(CompanyWareHouse companyWareHouse){
        Company company = companyRepository.findCompanyById(companyWareHouse.getCompany_id());
        if (company == null){
            throw new ApiException("Company not found");
        }

        if (companyWareHouse.getArea() == null) {
            throw new ApiException("Area cannot be null");
        }
        List<WareHouse> allWareHouse = wareHouseRepository.findAll();
        for (WareHouse w : allWareHouse){
            if (companyWareHouse.getArea() >= w.getMinArea() && companyWareHouse.getArea() <=w.getMaxArea()){
                if (!w.getIsBooked()){
                    companyWareHouse.setWareHouse_id(w.getId());
                    companyWareHouseRepository.save(companyWareHouse);
                    w.setIsBooked(true);
                    wareHouseRepository.save(w);

                }
            }
        }

    }
    public void changeOwnership(Integer wareHouse_id,Integer oldCompany_id, Integer newCompany_id) {
        Company oldCompany = companyRepository.findCompanyById(oldCompany_id);
        if (oldCompany == null) {
            throw new ApiException("Old Company not found");
        }
        Company newCompany = companyRepository.findCompanyById(newCompany_id);
        if (newCompany == null) {
            throw new ApiException("New Company not found");
        }
        CompanyWareHouse companyWareHouse = companyWareHouseRepository.findCompanyWareHouseByCompanyIdAnAndWarehouseId(oldCompany_id, wareHouse_id);
        if (companyWareHouse == null) {
            throw new ApiException("Old Company Warehouse not found");
        }
        companyWareHouse.setCompany_id(newCompany_id);
        companyWareHouseRepository.save(companyWareHouse);
        List<Branch> allBranchByCompanyId = branchRepository.findAllByCompanyId(oldCompany_id);
        for (Branch b : allBranchByCompanyId) {
            b.setCompany_id(newCompany_id);
            branchRepository.save(b);
        }
    }
}
