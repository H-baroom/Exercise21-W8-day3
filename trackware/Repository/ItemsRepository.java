package com.example.trackware.Repository;

import com.example.trackware.Model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemsRepository extends JpaRepository<Items, Integer> {
    Items findItemsById(Integer id);
    Items findItemsByName(String name);
}
