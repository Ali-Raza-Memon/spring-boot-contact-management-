package com.learning.jwt.repository;

import com.learning.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select user from  User user where user.fullName=?1")
    User findByName(String fullName);


}

