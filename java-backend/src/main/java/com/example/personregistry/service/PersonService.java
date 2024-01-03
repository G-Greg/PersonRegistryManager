package com.example.personregistry.service;

import com.example.personregistry.model.Address;
import com.example.personregistry.model.Contact;
import com.example.personregistry.model.Person;
import com.example.personregistry.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ContactService contactService;


    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public Person getPersonById(Long id) {
        var optional = personRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        return null;
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person updatePerson(Long id, Person updatedPerson) {
        var optionalPerson = personRepository.findById(id);

        if (optionalPerson.isPresent()) {
            Person existingPerson = optionalPerson.get();

            existingPerson.setFirstName(updatedPerson.getFirstName());
            existingPerson.setLastName(updatedPerson.getLastName());


            //Update Addresses by line
            var newAddresses = new ArrayList<Address>();
            for (int i = 0; i < existingPerson.getAddresses().size(); i++) {
                newAddresses.add(addressService.updateAddress(
                        existingPerson.getAddresses().get(i).getId(),
                        updatedPerson.getAddresses().get(i)
                ));
            }
            existingPerson.setAddresses(newAddresses);


            //Update Contacts by line
            var newContacts = new ArrayList<Contact>();
            for (int i = 0; i < existingPerson.getContacts().size(); i++) {
                newContacts.add(contactService.updateContact(
                        existingPerson.getAddresses().get(i).getId(),
                        updatedPerson.getContacts().get(i)
                ));
            }
            existingPerson.setContacts(newContacts);

            return personRepository.save(existingPerson);
        } else {

            throw new EntityNotFoundException("Person not found with id: " + id);
        }
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
