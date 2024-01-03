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
        var optional = repository.findById(id);
        if (optional.isPresent())
            return optional.get();
        return null;
    }

    public Contact createContact(Contact contact) {
        return null;
    }

    public Contact updateContact(Long id, Contact updatedContact) {
        var optional = repository.findById(id);

        if (optional.isPresent()) {
            Contact existingContact = optional.get();

            existingContact.setEmail(updatedContact.getEmail());
            existingContact.setTelephone(updatedContact.getTelephone());

            return repository.save(existingContact);
        } else {

            throw new EntityNotFoundException("Contact not found with id: " + id);
        }
    }

    public void deleteContact(Long id) {
        repository.deleteById(id);
    }
}
