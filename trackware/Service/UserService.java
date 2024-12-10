package com.example.trackware.Service;

import com.example.trackware.Api.ApiException;
import com.example.trackware.Model.User;
import com.example.trackware.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(Integer id,User user) {
        User UUser = userRepository.findUserById(id);
        if (UUser == null) {
            throw new ApiException("User not found");
        }
        UUser.setName(user.getName());
        UUser.setEmail(user.getEmail());
        userRepository.save(UUser);
    }

    public void deleteUser(Integer id) {
        User DUser = userRepository.findUserById(id);
        if(DUser == null) {
            throw new ApiException("User not found");
        }
        userRepository.delete(DUser);
    }



}
