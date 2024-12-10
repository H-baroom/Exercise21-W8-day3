package com.example.trackware.Repository;

import com.example.trackware.Model.User;
import com.example.trackware.Model.UserWareHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWareHouseRepository extends JpaRepository<UserWareHouse, Integer> {
    UserWareHouse findUserWareHouseById(Integer id);
}
