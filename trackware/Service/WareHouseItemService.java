package com.example.trackware.Service;

import com.example.trackware.Api.ApiException;
import com.example.trackware.Model.*;
import com.example.trackware.Repository.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WareHouseItemService {
    private final WareHouseItemRepository wareHouseItemRepository;
    private final WareHouseRepository wareHouseRepository;
    private final ItemsRepository ItemsRepository;
    private final CompanyRepository companyRepository;
    private final ItemsService itemsService;
    private final CompanyWareHouseRepository companyWareHouseRepository;


    public List<WareHouseItem> getAllWareHouseItems() {
        return wareHouseItemRepository.findAll();
    }

    public void addItemToWarehouse(Integer company_id,Integer warehouse_id, Integer item_id,Integer quantity) {
        WareHouse wareHouse = wareHouseRepository.findWareHouseById(warehouse_id);
        Items item = ItemsRepository.findItemsById(item_id);
        Company company = companyRepository.findCompanyById(company_id);
        CompanyWareHouse companyWareHouse = companyWareHouseRepository.findCompanyWareHouseByCompanyIdAnAndWarehouseId(company_id,warehouse_id);
        if (wareHouse == null){
            throw new ApiException("wareHouse not found");
        }
        if (item == null){
            throw new ApiException("item not found");
        }
        if (company == null){
            throw new ApiException("company not found");
        }
        if (companyWareHouse == null){
            throw new ApiException("companyWareHouse not found");
        }
        if (quantity <= item.getQuantity()){
            throw new ApiException("Quantity is not available");
        }
        WareHouseItem wareHouseItem = new WareHouseItem(warehouse_id,item_id,quantity);
        wareHouseItemRepository.save(wareHouseItem);


    }

    public void transferItems(Integer company_id,Integer SourceWarehouse_id, Integer destinationWarehouse_id, Integer item_id,Integer quantity) {
        WareHouseItem sourceWareHouseItem = wareHouseItemRepository.findWareHouseItemByWareHouseId(SourceWarehouse_id);
        WareHouseItem destinationWareHouseItem = wareHouseItemRepository.findWareHouseItemByWareHouseId(destinationWarehouse_id);
        Company company = companyRepository.findCompanyById(company_id);
        Items item = ItemsRepository.findItemsById(item_id);
        if (company == null) {
            throw new ApiException("Company not found");
        }
        if (sourceWareHouseItem == null) {
            throw new ApiException("Source WareHouseItem not found");
        }
        if (destinationWareHouseItem == null) {
            throw new ApiException("Destination WareHouseItem not found");
        }
        if (item == null) {
            throw new ApiException("Item not found");
        }
        if (quantity <= item.getQuantity()){
            throw new ApiException("Quantity is not available");
        }
        if (sourceWareHouseItem.getItem_id() == item_id){
            if (destinationWareHouseItem.getItem_id() == item_id){
                sourceWareHouseItem.setQuantity(sourceWareHouseItem.getQuantity() - quantity);
                destinationWareHouseItem.setQuantity(destinationWareHouseItem.getQuantity() + quantity);
                wareHouseItemRepository.save(sourceWareHouseItem);
                wareHouseItemRepository.save(destinationWareHouseItem);
            }else {
                addItemToWareHouseItem(destinationWareHouseItem.getId(),item_id,quantity);
                sourceWareHouseItem.setQuantity(sourceWareHouseItem.getQuantity() - quantity);
                wareHouseItemRepository.save(sourceWareHouseItem);
            }
        }
    }

    public void addItemToWareHouseItem(Integer wareHouse_id,Integer item_id,Integer quantity) {
        WareHouse wareHouse = wareHouseRepository.findWareHouseById(wareHouse_id);
        Items item = ItemsRepository.findItemsById(item_id);
        if (item == null) {
            throw new ApiException("Item not found");
        }
        if (quantity <= item.getQuantity()){
            throw new ApiException("Quantity is not available");
        }
        WareHouseItem addWareHouseItem = new WareHouseItem(wareHouse.getId(),item_id,quantity);
        wareHouseItemRepository.save(addWareHouseItem);
    }

    public List<Items> checkItemsExpiration(Integer wereHose_id){
        WareHouse wareHouse = wareHouseRepository.findWareHouseById(wereHose_id);
        List<Items> expiryItems = new ArrayList<>();
        if (wareHouse == null){
            throw new ApiException("wareHouse not found");
        }
        WareHouseItem wareHouseItem = wareHouseItemRepository.findWareHouseItemByWareHouseId(wereHose_id);
        if (wareHouseItem == null){
            throw new ApiException("wareHouseItem not found");
        }
        List<WareHouseItem> allWareHouseItem = wareHouseItemRepository.findAll();
        for (WareHouseItem whi : allWareHouseItem) {
            if (whi.getWareHouse_id() == wereHose_id){
                Items item = ItemsRepository.findItemsById(whi.getItem_id());
                itemsService.checkExpiredItems(item.getId());
                ItemsRepository.save(item);
                if (item.getIsExpired()){
                    expiryItems.add(item);
                }
            }
        }
        return expiryItems;
    }

    public List<WareHouse> getAllWareHousesForItem(Integer item_id) {
        Items item = ItemsRepository.findItemsById(item_id);
        List<WareHouse> wareHouses = new ArrayList<>();
        List<WareHouseItem> allWareHouseItem = wareHouseItemRepository.findAll();
        if (item == null){
            throw new ApiException("Item not found");
        }
        for (WareHouseItem whi : allWareHouseItem) {
            if (whi.getItem_id() == item_id){
                WareHouse wareHouse = wareHouseRepository.findWareHouseById(whi.getWareHouse_id());
                if (wareHouse != null){
                    wareHouses.add(wareHouse);
                }
            }
        }
        return wareHouses;
    }

    public void damagedItems(Integer company_id,Integer wareHouse_id ,Integer item_id,Integer quantity ) {
        Company company = companyRepository.findCompanyById(company_id);
        WareHouseItem wareHouseItem = wareHouseItemRepository.findWareHouseItemByWareHouseIdAndItemId(wareHouse_id,item_id);
        if (company == null){
            throw new ApiException("Company not found");
        }
        if (wareHouseItem == null){
            throw new ApiException("Ware House Item not found");
        }
        CompanyWareHouse companyWareHouse = companyWareHouseRepository.findCompanyWareHouseByCompanyIdAnAndWarehouseId(company_id,wareHouse_id);
        if (companyWareHouse == null){
            throw new ApiException("CompanyWareHouse not found");
        }
        wareHouseItem.setQuantity(wareHouseItem.getQuantity() - quantity);
        wareHouseItemRepository.save(wareHouseItem);

    }

    public List<Items> lowQuantity(Integer company_id,Integer wareHouse_id,Integer quantity) {
        List<WareHouseItem> wareHouseItem = wareHouseItemRepository.findAllWareHouseItemByWareHouseId(wareHouse_id);
        Company company = companyRepository.findCompanyById(company_id);
        List<Items> items = new ArrayList<>();
        if (wareHouseItem == null){
            throw new ApiException("Ware House Item not found");
        }
        if (company == null){
            throw new ApiException("company not found");
        }
        for (WareHouseItem whi : wareHouseItem) {
            if (whi.getQuantity() < quantity){
                Items item = ItemsRepository.findItemsById(whi.getItem_id());
                items.add(item);
            }
        }
        return items;
    }


}
