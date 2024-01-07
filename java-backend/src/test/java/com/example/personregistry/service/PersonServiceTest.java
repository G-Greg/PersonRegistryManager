package com.example.personregistry.service;

import com.example.personregistry.exception.AddressException;
import com.example.personregistry.model.Address;
import com.example.personregistry.model.Contact;
import com.example.personregistry.model.Person;
import com.example.personregistry.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;


    @Test
    void savePerson_returnPerson() {
        var person = Person.builder()
                .id(1L)
                .firstName("Márton")
                .lastName("Sas")
                .addresses(Arrays.asList(
                        Address.builder().zip(1049).city("Budapest").street("Petőfi utca 9.").isPermanent(0).build()
                ))
                .contacts(Arrays.asList(
                        Contact.builder().email("sas.marton@email.com").telephone("+36 30 558 9854").build()
                ))
                .build();

        when(personRepository.save(any(Person.class))).thenReturn(person);

        Person createdPerson = personService.createPerson(person);

        assertThat(createdPerson).isNotNull();
        assertThat(createdPerson.getFirstName()).isEqualTo(person.getFirstName());
    }

    @Test
    void savePersonWithMoreAddresses_returnAddressException() {
        var person = Person.builder()
                .firstName("Márton")
                .lastName("Sas")
                .addresses(Arrays.asList(
                        Address.builder().zip(1049).city("Budapest").street("Petőfi utca 9.").isPermanent(0).build(),
                        Address.builder().zip(1088).city("Budapest").street("Nagy Lajos utca 10.").isPermanent(1).build(),
                        Address.builder().zip(1088).city("Budapest").street("Nagy Lajos utca 10.").isPermanent(0).build()
                ))
                .contacts(Arrays.asList(
                        Contact.builder().email("sas.marton@email.com").telephone("+36 30 558 9854").build()
                ))
                .build();


        when(personService.createPerson(person)).thenThrow(new AddressException("Egy személyhez legfeljebb két cím tartozhat!"));
        assertThrows(AddressException.class, () -> personService.createPerson(person));
    }

    @Test
    void savePersonWithWrongAddresses_returnAddressException() {
        var person = Person.builder()
                .firstName("Márton")
                .lastName("Sas")
                .addresses(Arrays.asList(
                        Address.builder().zip(1049).city("Budapest").street("Petőfi utca 9.").isPermanent(1).build(),
                        Address.builder().zip(1088).city("Budapest").street("Nagy Lajos utca 10.").isPermanent(1).build()
                ))
                .contacts(Arrays.asList(
                        Contact.builder().email("sas.marton@email.com").telephone("+36 30 558 9854").build()
                ))
                .build();

        when(personService.createPerson(person)).thenThrow(new AddressException("Egyszerre csak egy állandó címmel rendelkezhet!"));
        assertThrows(AddressException.class, () -> personService.createPerson(person));
    }
}
