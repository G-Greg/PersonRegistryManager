package com.dpd.personregistrymanager.service;


import com.dpd.personregistrymanager.models.Address;
import com.dpd.personregistrymanager.models.Person;
import com.dpd.personregistrymanager.repositories.PersonRepository;
import com.dpd.personregistrymanager.services.PersonService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    private static Person person1;
    private static Person person2;
    private static Person person3;

    @BeforeAll
    static void init() {
        person1 = Person.builder()
                .id(1L)
                .name("Sas Márton")
                .birthdate("2000-10-10")
                .birthplace("Budapest")
                .TAJ(3657485)
                .taxId(7754342)
                .email("sas.marton@email.com")
                .addresses(Arrays.asList(
                        Address.builder()
                                .zipCode(1040)
                                .city("Budapest")
                                .street("Petőfi utca")
                                .houseNumber(9).build()
                ))
                .phoneNumbers(List.of("+36305589854"))
                .build();

        person2 = Person.builder()
                .id(2L)
                .name("Frédéric Roland")
                .birthdate("1970-10-10")
                .birthplace("Budapest")
                .TAJ(3657485)
                .taxId(7754342)
                .email("fredERIC@email.com")
                .addresses(Arrays.asList())
                .phoneNumbers(List.of("+36304356854"))
                .build();


        person3 = Person.builder()
                .id(3L)
                .name("Laura Ábel")
                .birthdate("2001-03-13")
                .birthplace("Budapest")
                .TAJ(3257467)
                .taxId(5254352)
                .email("laura@email.com")
                .addresses(Arrays.asList(
                        Address.builder()
                                .zipCode(1040)
                                .city("Budapest")
                                .street("Körte utca")
                                .houseNumber(13).build()
                ))
                .phoneNumbers(List.of("+36703339851"))
                .build();
    }

    @Test
    void savePersonNormalBehavior() {
        when(personRepository.save(any(Person.class))).thenReturn(person1);
        Person createdPerson = personService.createPerson(person1);
        assertThat(createdPerson).isNotNull();
        assertThat(createdPerson.getName()).isEqualTo(person1.getName());
    }

    @Test
    void savePersonShouldReturnErrorWhenAddressIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> personService.createPerson(person2));
    }

    @Test
    void findPersonById() {
        when(personRepository.findById(1L)).thenReturn(java.util.Optional.of(person1));
        Person foundPerson = personService.getPerson(1L);
        assertThat(foundPerson).isNotNull();
        assertThat(foundPerson.getName()).isEqualTo(person1.getName());
    }

    @Test
    void findAllPerson() {
        when(personRepository.findAll()).thenReturn(Arrays.asList(person1, person2, person3));
        List<Person> foundPerson = personService.getPersons();
        assertThat(foundPerson).isNotNull();
        assertThat(foundPerson.size()).isEqualTo(3);
    }

    @Test
    void findPersonByIdWithNull() {
        when(personRepository.findById(10L)).thenReturn(java.util.Optional.empty());
        Person foundPerson = personService.getPerson(10L);
        assertThat(foundPerson).isNull();
    }

    @Test
    void updatePersonNormalBehavior() {
        when(personRepository.findById(1L)).thenReturn(java.util.Optional.of(person1));
        when(personRepository.save(any(Person.class))).thenReturn(person1);
        Person updatedPerson = personService.updatePerson(1L, person1);
        assertThat(updatedPerson).isNotNull();
        assertThat(updatedPerson.getName()).isEqualTo(person1.getName());
    }

    @Test
    void updatePersonShouldReturnErrorWhenPersonNotFound() {
        when(personRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> personService.updatePerson(1L, person1));
    }

    @Test
    void updatePersonShouldReturnErrorWhenAddressIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> personService.updatePerson(2L, person2));
    }

    @Test
    void deletePersonNormalBehavior() {
        personService.deletePerson(1L);
        verify(personRepository, times(1)).deleteById(1L);
    }

    @Test
    void GRDPRPersonDeleteNormalBehavior() {
        when(personRepository.findById(3L)).thenReturn(java.util.Optional.of(person3));
        personService.GRDPRPersonDelete(3L);
        assertThat(person3.getName()).isEqualTo("Deleted user");
        assertThat(person3.getBirthdate()).isEqualTo("1900-01-01");
        assertThat(person3.getBirthplace()).isEqualTo("");
        assertThat(person3.getTAJ()).isEqualTo(0);
        assertThat(person3.getTaxId()).isEqualTo(0);
        assertThat(person3.getEmail()).isEqualTo("");
        assertThat(person3.getAddresses().get(0).getZipCode()).isEqualTo(0);
        assertThat(person3.getAddresses().get(0).getCity()).isEqualTo("");
        assertThat(person3.getAddresses().get(0).getStreet()).isEqualTo("");
        assertThat(person3.getAddresses().get(0).getHouseNumber()).isEqualTo(0);
        assertThat(person3.getPhoneNumbers().get(0)).isEqualTo("");
    }
}
