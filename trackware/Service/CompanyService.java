package com.example.trackware.Service;

import com.example.trackware.Api.ApiException;
import com.example.trackware.Model.Company;
import com.example.trackware.Repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    public void updateCompany(Integer id, Company company) {
        Company updateCompany = companyRepository.findCompanyById(id);
        if (updateCompany == null){
            throw new ApiException("Company not found");
        }
        updateCompany.setName(company.getName());

        updateCompany.setEmail(company.getEmail());
        companyRepository.save(updateCompany);

    }

    public void deleteCompany(Integer id) {
        Company company = companyRepository.findCompanyById(id);
        if (company != null){
            companyRepository.delete(company);
        }
    }
}
