package com.example.personregistry.service;

import com.example.personregistry.model.Person;
import com.example.personregistry.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public List<Person> getPersons() {
        return repository.findAll();
    }

    public Person getPersonById(Long id) {
        var optional = repository.findById(id);
        if (optional.isPresent())
            return optional.get();
        return null;
    }

    public Person createPerson(Person person) {
        return null;
    }

    public Person updatePerson(Long id, Person updatedPerson) {
        return null;
    }

    public void deletePerson(Long id) {
        repository.deleteById(id);
    }
}
