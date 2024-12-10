package com.example.trackware.Service;

import com.example.trackware.Api.ApiException;
import com.example.trackware.Model.*;
import com.example.trackware.Repository.*;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WareHouseService {
    private final WareHouseRepository wareHouseRepository;
    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final ItemsRepository itemsRepository;
    private final WareHouseItemRepository wareHouseItemRepository;
    private final CompanyRepository companyRepository;


    public List<WareHouse> getAllWareHouses() {
        return wareHouseRepository.findAll();
    }


    public void addWareHouse(WareHouse wareHouse) {
        wareHouseRepository.save(wareHouse);
    }


    public void updateWareHouse(Integer id, WareHouse wareHouse) {
        WareHouse wareHouse1 = wareHouseRepository.findWareHouseById(id);
        if (wareHouse1 == null) {
            throw new ApiException("WareHouse not found");
        }
        wareHouse1.setName(wareHouse.getName());
        wareHouse1.setLocation(wareHouse.getLocation());
        wareHouse1.setMinArea(wareHouse.getMinArea());
        wareHouse1.setMaxArea(wareHouse.getMaxArea());
        wareHouse1.setPrice(wareHouse.getPrice());
        wareHouse1.setIsBooked(wareHouse.getIsBooked());
        wareHouseRepository.save(wareHouse1);
    }

    public void deleteWareHouse(Integer id) {
        WareHouse wareHouse = wareHouseRepository.findWareHouseById(id);
        if (wareHouse == null) {
            throw new ApiException("WareHouse not found");
        }
        wareHouseRepository.delete(wareHouse);
    }




}
