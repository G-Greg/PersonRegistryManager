package com.dpd.personregistrymanager.services;

import com.dpd.personregistrymanager.models.Address;
import com.dpd.personregistrymanager.models.Person;
import com.dpd.personregistrymanager.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final AddressService addressService;

    @Autowired
    public PersonService(PersonRepository personRepository, AddressService addressService) {

        this.personRepository = personRepository;
        this.addressService = addressService;
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public Person getPerson(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person createPerson(Person person) {
        if (person.getAddresses().isEmpty()) {
            throw new IllegalArgumentException("Cannot create person without addresses");
        }


        for (Address address : person.getAddresses()) {
            address.setPerson(person);
        }
        return personRepository.save(person);
    }

    public Person updatePerson(Long id, Person person) {
        if (person.getAddresses().isEmpty()) {
            throw new IllegalArgumentException("Cannot create person without addresses");
        }

        Person existingPerson = personRepository.findById(id).orElse(null);
        if (existingPerson == null) {
            throw new IllegalArgumentException("Person not found");
        }

        for (Address address : person.getAddresses()) {
            if (address.getId() == null) {
                address.setPerson(person);
            } else {
                Address existingAddress = addressService.getAddress(address.getId());
                if (existingAddress == null) {
                    throw new IllegalArgumentException("Address not found");
                }
                address.setPerson(person);
            }
        }

        existingPerson.setName(person.getName());
        existingPerson.setBirthdate(person.getBirthdate());
        existingPerson.setBirthplace(person.getBirthplace());
        existingPerson.setTAJ(person.getTAJ());
        existingPerson.setTaxId(person.getTaxId());
        existingPerson.setEmail(person.getEmail());
        existingPerson.setPhoneNumbers(person.getPhoneNumbers());

        return personRepository.save(existingPerson);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public void GRDPRPersonDelete(Long id) {
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            throw new IllegalArgumentException("Person not found");
        }

        person.setName("Deleted user");
        person.setBirthdate("1900-01-01");
        person.setBirthplace("");
        person.setTAJ(0);
        person.setTaxId(0);
        person.setEmail("");

        for (Address address : person.getAddresses()) {
            address.setZipCode(0);
            address.setCity("");
            address.setStreet("");
            address.setHouseNumber(0);
        }

        person.setPhoneNumbers(new ArrayList<>());
        personRepository.save(person);
    }
}
