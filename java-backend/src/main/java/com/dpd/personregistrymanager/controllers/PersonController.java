package com.dpd.personregistrymanager.controllers;

import com.dpd.personregistrymanager.models.Person;
import com.dpd.personregistrymanager.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPersons() {
        return ResponseEntity.ok(personService.getPersons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getPerson(id));
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        return ResponseEntity.ok(personService.createPerson(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        return ResponseEntity.ok(personService.updatePerson(id, person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/gdpr/{id}")
    public ResponseEntity<Void> GRDPRPersonDelete(@PathVariable Long id) {
        personService.GRDPRPersonDelete(id);
        return ResponseEntity.noContent().build();
    }
}
