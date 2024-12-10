package com.example.trackware.Repository;


import com.example.trackware.Model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
    Branch findBranchById(Integer id);
    @Query("select b from Branch b where b.company_id=?1")
    List<Branch> findAllByCompanyId(Integer CompanyId);

    @Query("select b from Branch b where b.id=?1 and b.company_id=?2")
    Branch findBranchByIdAndCompanyId(Integer id, Integer CompanyId);

    @Query("select b from Branch b where b.id=?1 and b.company_id=?2 and b.wareHouse_id=?3")
    Branch findBranchByIdAndCompanyIdAndwareHouseId(Integer id, Integer CompanyId, Integer wareHouseId);

}
