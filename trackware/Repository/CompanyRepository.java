package com.example.trackware.Repository;

import com.example.trackware.Model.Company;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    Company findCompanyById(Integer id);
}
