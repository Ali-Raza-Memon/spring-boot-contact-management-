package com.learning.jwt.repository;

import com.learning.jwt.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {

//    List<Contact> findByUserName(String name);
    @Query("select contact from Contact contact where contact.user.id=?1")
    List<Contact> findByUserId(int userId);

}