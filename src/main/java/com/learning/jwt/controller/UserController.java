package com.learning.jwt.controller;

import com.learning.jwt.dto.UserDto;
import com.learning.jwt.entity.User;
import com.learning.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/findAll")
    public ResponseEntity<List<User>> findAllUser(){
        try{
            List<User> user = userService.getUsers();
            if(user != null)
                return ResponseEntity.status(HttpStatus.OK).body(user);
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserDto request) {
        try {
            User addedUser = userService.saveUser(request);
            if (addedUser != null)
                return ResponseEntity.status(HttpStatus.CREATED).body(addedUser);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not saved!");
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody User user) throws Exception {
        try {
            User existingUser = userService.updateUser(user);
            if (existingUser != null)
                return ResponseEntity.status(HttpStatus.OK).body(existingUser);
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found!");
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Due to some error.User has not been updated yet!");
        }
    }

    @GetMapping("/name")
    public ResponseEntity<?> findByName(@RequestParam(name = "name") String fullName){

        try{
            User user = userService.findByName(fullName);
            if(user != null)
                return ResponseEntity.status(HttpStatus.FOUND).body(user);
            else
                throw new UsernameNotFoundException("User Not Found");

        }catch (Exception ex){
            // Handle other exception
           return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }



    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam(name = "userId") Integer userId) throws Exception {

        try {
            User user = userService.getUserById(userId);
            if (user != null)
                return ResponseEntity.status(HttpStatus.FOUND).body(user);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }

    }


    @DeleteMapping("/delete/id")
    public ResponseEntity<String> deleteById(@RequestParam(name ="id") int id){
        try{
            String result = userService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }catch(UsernameNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }





}

