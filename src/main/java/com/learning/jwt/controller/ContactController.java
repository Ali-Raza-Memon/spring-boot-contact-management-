package com.learning.jwt.controller;

import com.learning.jwt.dto.ContactDto;
import com.learning.jwt.entity.Contact;
import com.learning.jwt.entity.User;
import com.learning.jwt.repository.ContactRepository;
import com.learning.jwt.repository.UserRepository;
import com.learning.jwt.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserRepository userRepository;


    //getAllContacts
    @GetMapping("/findAll")
    public ResponseEntity<List<Contact>> findAllContacts(){
        try {
            List<Contact> contacts = contactService.getAllContacts();
            if (contacts != null)
                return ResponseEntity.status(HttpStatus.FOUND).body(contacts);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Saving data
    @PostMapping("/add")
    public ResponseEntity<?> addContact(@RequestBody ContactDto contact) throws Exception {
        try {
            User user = userRepository.findById(contact.getUserId()).orElseThrow(() -> new Exception("user not found"));
            Contact addedContact = new Contact();
            addedContact.setContact(contact.getContact());
            addedContact.setUser(user);
            contactRepository.save(addedContact);
            if (addedContact != null)
                return ResponseEntity.status(HttpStatus.CREATED).body(addedContact);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user not found!");
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Due to some errors,contact is not added yet");
        }
    }

    @GetMapping("/find/id")
    public ResponseEntity<?> findById(@RequestParam(name="id") int id){
        try {
            Contact contact = contactService.getContactById(id);
            if (contact != null)
                return ResponseEntity.status(HttpStatus.FOUND).body(contact);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact is not found!");
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Contact contact){
        try {
            Contact existingContact = contactService.updateContact(contact);
            if (existingContact != null)
                return ResponseEntity.status(HttpStatus.OK).body(existingContact);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contact not found");
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Due to some errors,contact has not been updated!");
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable("id") int id) {
        try {
            String result = contactService.deleteContact(id);
            if (result != null) {
                return ResponseEntity.status(HttpStatus.FOUND).body(result);
            }
            return ResponseEntity.notFound().build();
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();  // Log the exception stack trace
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }




}

