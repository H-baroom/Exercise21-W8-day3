package com.example.trackware.Service;

import com.example.trackware.Api.ApiException;
import com.example.trackware.Model.Category;
import com.example.trackware.Model.Items;
import com.example.trackware.Model.WareHouse;
import com.example.trackware.Repository.CategoryRepository;
import com.example.trackware.Repository.ItemsRepository;
import com.example.trackware.Repository.OrderRepository;
import com.example.trackware.Repository.WareHouseRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemsService {
    private final ItemsRepository itemsRepository;
    private final WareHouseRepository wareHouseRepository;
    private final CategoryRepository categoryRepository;

    public List<Items> getAllItems(){
        return itemsRepository.findAll();
    }

    public void addItem(Items item){
        Category category = categoryRepository.findCategoryById(item.getCategory_id());
        Items item1 = item;
        if (category == null){
            throw new ApiException("Category not found");
        }
        itemsRepository.save(item1);
    }

    public void updateItem(Integer id,Items item){
        Items updateItems = itemsRepository.findItemsById(id);
        Category category = categoryRepository.findCategoryById(item.getCategory_id());
        if (updateItems != null) {
            if (category != null) {
                updateItems.setName(item.getName());
                updateItems.setPrice(item.getPrice());
                updateItems.setQuantity(item.getQuantity());
                updateItems.setExpiration_date(item.getExpiration_date());
                updateItems.setCategory_id(item.getCategory_id());
                updateItems.setIsExpired(item.getIsExpired());
                itemsRepository.save(updateItems);
            }
                throw new ApiException("Category not found");
        }
        throw new ApiException("Item not found");
    }

    public void deleteItem(Integer id){
        Items item = itemsRepository.findItemsById(id);
        if (item == null) {
            throw new ApiException("Item not found");
        }
        itemsRepository.delete(item);
    }

    public void checkExpiredItems(Integer item_id) {
        Items item = itemsRepository.findItemsById(item_id);
        if (item == null) {
            throw new ApiException("item not found");
        }
        if (item.getExpiration_date().isBefore(LocalDateTime.now())){
            item.setIsExpired(true);
            itemsRepository.save(item);
        }

    }

    public void changeCategoryOfItem(Integer item_id, Integer newCategory_id){
        Items item = itemsRepository.findItemsById(item_id);
        Category category = categoryRepository.findCategoryById(newCategory_id);
        if (item == null) {
            throw new ApiException("item not found");
        }
        if (category == null) {
            throw new ApiException("Category not found");
        }
        item.setCategory_id(newCategory_id);
        itemsRepository.save(item);
    }


    //////////////////////////////////////////////////////////////////////////
    public Items cheapestPrice(String item_name){
        Items item = itemsRepository.findItemsByName(item_name);
        List<Items> allItems = itemsRepository.findAll();

        if (item == null) {
            throw new ApiException("item not found");
        }
        Items cheapestPrice = new Items(100000);
        for (Items i : allItems) {
            if (i.getName().equalsIgnoreCase(item_name)){
                if ( i.getPrice() < cheapestPrice.getPrice()){
                    cheapestPrice = i;
                }
            }
        }
        return cheapestPrice;
    }


}
