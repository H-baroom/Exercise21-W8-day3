package com.example.trackware.Controller;

import com.example.trackware.Api.ApiResponse;
import com.example.trackware.Model.Category;
import com.example.trackware.Model.Items;
import com.example.trackware.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getAllCategories() {
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category) {

        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("Category added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @RequestBody @Valid Category category){
        categoryService.updateCategory(id,category);
        return ResponseEntity.status(200).body(new ApiResponse("Category updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        return ResponseEntity.status(200).body(new ApiResponse("Category deleted"));
    }

    @GetMapping("/getAllItemsByCategoryId/{category_id}")
    public ResponseEntity getCategoryById(@PathVariable Integer category_id){
        List<Items> allItemsByCategoryId = categoryService.getAllItemsByCategoryId(category_id);
        return ResponseEntity.status(200).body(allItemsByCategoryId);
    }
}
