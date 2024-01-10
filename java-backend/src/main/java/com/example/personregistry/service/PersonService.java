package com.example.personregistry.service;

import com.example.personregistry.model.Address;
import com.example.personregistry.model.Contact;
import com.example.personregistry.model.Person;
import com.example.personregistry.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("A keresett személy nem található! id:" + id));
    }

    public Person createPerson(Person person) {
        for (Address address : person.getAddresses()) {
            address.setPerson(person);
        }

        for (Contact contact : person.getContacts()) {
            contact.setPerson(person);
        }
        return personRepository.save(person);
    }

    public Person updatePerson(Long id, Person updatedPerson) {
        var existingPerson = getPersonById(id);

        if (updatedPerson.getId() == null) {
            updatedPerson.setId(existingPerson.getId());
        }

        existingPerson.setFirstName(updatedPerson.getFirstName());
        existingPerson.setLastName(updatedPerson.getLastName());

        for (Address address : updatedPerson.getAddresses()) {
            address.setPerson(updatedPerson);
        }
        existingPerson.setAddresses(updatedPerson.getAddresses());

        for (Contact contact : updatedPerson.getContacts()) {
            contact.setPerson(updatedPerson);
        }
        existingPerson.setContacts(updatedPerson.getContacts());

        return personRepository.save(existingPerson);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
