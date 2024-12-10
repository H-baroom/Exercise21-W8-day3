package com.example.trackware.Service;

import com.example.trackware.Api.ApiException;
import com.example.trackware.Model.Company;
import com.example.trackware.Model.User;
import com.example.trackware.Model.UserWareHouse;
import com.example.trackware.Model.WareHouse;
import com.example.trackware.Repository.CompanyRepository;
import com.example.trackware.Repository.UserRepository;
import com.example.trackware.Repository.UserWareHouseRepository;
import com.example.trackware.Repository.WareHouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserWareHouseService {
    private final UserWareHouseRepository userWareHouseRepository;
    private final UserRepository userRepository;
    private final WareHouseRepository wareHouseRepository;

    public List<UserWareHouse> getAllUserWareHouses() {
        return userWareHouseRepository.findAll();
    }

    public void addUserWareHouse(UserWareHouse userWareHouse) {
        User user = userRepository.findUserById(userWareHouse.getUser_id());
        if (user == null) {
            throw new ApiException("user not found");
        }

        List<WareHouse> allWareHouse = wareHouseRepository.findAll();
        for (WareHouse w : allWareHouse){
            if (userWareHouse.getArea() >= w.getMinArea() && userWareHouse.getArea() <=w.getMaxArea()){
                if (!w.getIsBooked()){
                    userWareHouse.setWareHouse_id(w.getId());
                    w.setIsBooked(true);
                    wareHouseRepository.save(w);
                    userWareHouseRepository.save(userWareHouse);
                }
            }
        }
    }
}
