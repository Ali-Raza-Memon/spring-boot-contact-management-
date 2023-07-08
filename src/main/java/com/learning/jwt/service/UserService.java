package com.learning.jwt.service;


import com.learning.jwt.dto.UserDto;
import com.learning.jwt.entity.User;
import com.learning.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User saveUser(UserDto dto) {
        User user=new User();
        user.setAddress(dto.getAddress());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        user.setCnic(dto.getCnic());
        return userRepository.save(user);
    }

    public String deleteById(int userId) {
        userRepository.deleteById(userId);
        return "User deleted"+userId;
    }

    public User updateUser(User user) throws Exception {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(()->new Exception("user not found"));
        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setAddress(user.getAddress());
        existingUser.setPassword(user.getPassword());
        existingUser.setCnic(user.getCnic());
        return userRepository.save(existingUser);
    }
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).get();
    }


}

