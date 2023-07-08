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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserRepository userRepository;

//    @GetMapping("/findByName")
//    public ResponseEntity<List<Contact>> getContactsByUserName(@RequestParam (name = "fullName") String name) {
//        List<Contact> contact = contactService.getContactsByUserName(name);
//        if(contact != null)
//            return ResponseEntity.status(HttpStatus.FOUND).body(contact);
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//    }

    //getAllContacts
    @GetMapping("/allContacts")
    public ResponseEntity<List<Contact>> findAllContacts(){
        List<Contact> contacts = contactService.getAllContacts();
        if(contacts != null)
            return ResponseEntity.status(HttpStatus.FOUND).body(contacts);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    //Saving data
    @PostMapping("/addContact")
    public ResponseEntity<?> addContact(@RequestBody ContactDto contact) throws Exception {
        User user = userRepository.findById(contact.getUserId()).orElseThrow(()->new Exception("user not found"));
        Contact addedContact = new Contact();
        addedContact.setContact(contact.getContact());
        addedContact.setUser(user);
        contactRepository.save(addedContact);
        if (addedContact != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(addedContact);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user not found!");
    }

    @GetMapping("/contact/id")
    public ResponseEntity<?> findById(@RequestParam(name="id") int id){
        Contact contact = contactService.getContactById(id);
        if(contact != null)
            return ResponseEntity.status(HttpStatus.FOUND).body(contact);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/contact/update")
    public ResponseEntity<?> update(@RequestBody Contact contact){
        Contact existingContact = contactService.updateContact(contact);
        if(existingContact != null)
            return ResponseEntity.status(HttpStatus.OK).body(existingContact);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


    @DeleteMapping("/delete")
    public String deleteContact(@RequestParam(name ="id") int contactId){
        return contactService.deleteContact(contactId);
    }

}

