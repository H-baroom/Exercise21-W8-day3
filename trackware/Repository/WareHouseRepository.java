package com.example.trackware.Repository;

import com.example.trackware.Model.User;
import com.example.trackware.Model.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WareHouseRepository extends JpaRepository<WareHouse, Integer> {
    WareHouse findWareHouseById(Integer id);
}
