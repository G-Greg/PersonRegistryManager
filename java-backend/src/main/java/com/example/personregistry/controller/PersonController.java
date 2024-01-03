package com.example.personregistry.controller;

import com.example.personregistry.model.Person;
import com.example.personregistry.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping
    public ResponseEntity<List<Person>> getPersons(){
        return ResponseEntity.ok(service.getPersons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = service.getPersonById(id);
        return ResponseEntity.ok(person);
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person createdPerson = service.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        Person updatedPerson = service.updatePerson(id, person);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        service.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
