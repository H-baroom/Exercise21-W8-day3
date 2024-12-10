package com.example.trackware.Service;

import com.example.trackware.Api.ApiException;
import com.example.trackware.Model.Category;
import com.example.trackware.Model.Items;
import com.example.trackware.Repository.CategoryRepository;
import com.example.trackware.Repository.ItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ItemsRepository itemsRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void updateCategory(Integer id,Category category) {
        Category updateCategory = categoryRepository.findCategoryById(id);
        if (updateCategory == null) {
            throw new ApiException("Category not found");
        }
        updateCategory.setName(category.getName());
        updateCategory.setDescription(category.getDescription());
        categoryRepository.save(updateCategory);
    }

    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findCategoryById(id);
        if (category == null) {
            throw new ApiException("Category not found");
        }
        categoryRepository.delete(category);
    }

    public List<Items> getAllItemsByCategoryId(Integer category_id) {
        Category category = categoryRepository.findCategoryById(category_id);
        if (category == null) {
            throw new ApiException("Category not found");
        }
        List<Items> itemsByCategory = new ArrayList<>();
        List<Items> allItems = itemsRepository.findAll();
        for (Items item : allItems) {
            if (item.getCategory_id() == category.getId()) {
                itemsByCategory.add(item);
            }
        }
        return itemsByCategory;
    }
}
