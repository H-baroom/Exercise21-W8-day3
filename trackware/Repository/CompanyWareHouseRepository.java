package com.example.trackware.Repository;

import com.example.trackware.Model.Company;
import com.example.trackware.Model.CompanyWareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyWareHouseRepository extends JpaRepository<CompanyWareHouse,Integer> {
    CompanyWareHouse findCompanyWareHouseById(Integer id);
    @Query("select cwh from CompanyWareHouse cwh where cwh.company_id=?1 and cwh.wareHouse_id=?2")
    CompanyWareHouse findCompanyWareHouseByCompanyIdAnAndWarehouseId(Integer companyId, Integer warehouseId);
}
