package com.example.trackware.Repository;

import com.example.trackware.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
    Orders findOrderById(Integer id);
    @Query("select o from Orders o where o.branch_id=?1")
    List<Orders> findOrderByBranch_id(Integer branch_id);
}
