package com.example.trackware.Repository;

import com.example.trackware.Model.Branch;
import com.example.trackware.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoryById(Integer id);
}
