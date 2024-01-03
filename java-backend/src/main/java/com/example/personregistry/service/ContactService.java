package com.example.personregistry.service;

import com.example.personregistry.model.Contact;
import com.example.personregistry.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    public List<Contact> getContacts() {
        return repository.findAll();
    }

    public Contact getContactById(Long id) {
        return null;
    }

    public Contact createContact(Contact contact) {
        return null;
    }

    public Contact updateContact(Long id, Contact updatedContact) {
        return null;
    }

    public void deleteContact(Long id) {
        repository.deleteById(id);
    }
}
