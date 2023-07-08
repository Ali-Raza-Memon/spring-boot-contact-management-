package com.learning.jwt.service;

import com.learning.jwt.entity.Contact;
import com.learning.jwt.entity.User;
import com.learning.jwt.repository.ContactRepository;
import com.learning.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserRepository userRepository;


    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }


    public String deleteContact(int contactId) {
        contactRepository.deleteById(contactId);
        return "Contact deleted "+contactId;
    }


    public Contact updateContact(Contact contact) {

        Contact existingContact = contactRepository.findById(contact.getId()).orElse(null);
        existingContact.setContact(contact.getContact());
        return contactRepository.save(existingContact);

    }


    public Contact getContactById(int contactId) {
        return contactRepository.findById(contactId).get();
    }



//    public List<Contact> getContactsByUserName(String name) {
//        return contactRepository.findByUserName(name);
//    }


    public List<Contact> getContactsByUserName(String userName) {
        User user = userRepository.findByName(userName);
        if (user != null) {
            return contactRepository.findByUserId(user.getId());
        }
        return Collections.emptyList();
    }

}

