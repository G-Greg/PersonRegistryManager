package com.example.personregistry.service;

import com.example.personregistry.model.Contact;
import com.example.personregistry.repository.ContactRepository;
import jakarta.persistence.EntityNotFoundException;
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
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("A keresett elérhetőség nem található! id:" + id));
    }

    public Contact updateContact(Long id, Contact updatedContact) {
        var existingContact = getContactById(id);

        existingContact.setEmail(updatedContact.getEmail());
        existingContact.setTelephone(updatedContact.getTelephone());

        return repository.save(existingContact);
    }

    public void deleteContact(Long id) {
        repository.deleteById(id);
    }
}
