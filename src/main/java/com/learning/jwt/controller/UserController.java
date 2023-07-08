package com.learning.jwt.controller;

import com.learning.jwt.dto.UserDto;
import com.learning.jwt.entity.User;
import com.learning.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/allUsers")
    public ResponseEntity<List<User>> findAllUser(){
        List<User> user = userService.getUsers();
        if(user != null)
            return ResponseEntity.status(HttpStatus.OK).body(user);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody UserDto request) {
        User addedUser = userService.saveUser(request);
        if (addedUser != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(addedUser);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody User user) throws Exception {
        User existingUser = userService.updateUser(user);
        if(existingUser != null)
            return ResponseEntity.status(HttpStatus.OK).body(existingUser);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/user/fullName")
    public ResponseEntity<?> findByName(@RequestParam(name = "fullName") String fullName){
        User user = userService.findByName(fullName);
        if(user != null)
           return ResponseEntity.status(HttpStatus.FOUND).body(user);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }



    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam(name = "userId") Integer userId) throws Exception {

        try {
            User user = userService.getUserById(userId);
            if (user != null)
                return ResponseEntity.status(HttpStatus.FOUND).body(user);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("user not found");
        }

    }


    @DeleteMapping("/delete/{userId}")
    public String deleteById(@PathVariable int userId){
        return userService.deleteById(userId);
    }


}

