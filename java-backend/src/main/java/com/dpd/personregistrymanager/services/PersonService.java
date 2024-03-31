package com.dpd.personregistrymanager.services;

import com.dpd.personregistrymanager.models.Address;
import com.dpd.personregistrymanager.models.Person;
import com.dpd.personregistrymanager.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
        for (Address address : person.getAddresses()) {
            address.setPerson(person);
            addressService.createAddress(address);
        }
        return personRepository.save(person);
    }

    public Person updatePerson(Long id, Person person) {
        return personRepository.save(person);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }


}
