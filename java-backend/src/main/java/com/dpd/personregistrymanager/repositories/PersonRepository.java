package com.dpd.personregistrymanager.repositories;

import com.dpd.personregistrymanager.models.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findAll();
}
