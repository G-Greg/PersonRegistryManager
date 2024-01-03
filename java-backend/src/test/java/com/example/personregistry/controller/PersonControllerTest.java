package com.example.personregistry.controller;

import com.example.personregistry.model.Address;
import com.example.personregistry.model.Contact;
import com.example.personregistry.model.Person;
import com.example.personregistry.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(controllers = PersonController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private PersonService personService;

    @Autowired
    private ObjectMapper objectMapper;
    private final List<Person> persons = new ArrayList<>();

    @BeforeEach
    public void init() throws Exception {
        // Személy #1
        persons.add(Person.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .addresses(Arrays.asList(
                        Address.builder().zip(1045).city("Budapest").street("Petőfi utca 5.").isPermanent(0).build()
                ))
                .contacts(Arrays.asList(
                        Contact.builder().email("teszt@email.com").telephone("+36 30 548 9618").build()
                ))
                .build());

        // Személy #2
        persons.add(Person.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Doe")
                .addresses(Arrays.asList(
                        Address.builder().zip(1301).city("Budapest").street("Rákóczi út 3.").isPermanent(0).build(),
                        Address.builder().zip(1401).city("Budapest").street("Deák tér 4.").isPermanent(1).build()
                ))
                .contacts(Arrays.asList(
                        Contact.builder().email("teszt3@email.com").telephone("+36 30 548 9620").build(),
                        Contact.builder().email("teszt4@email.com").telephone("+36 30 548 9621").build()
                ))
                .build());

        // Személy #3
        persons.add(Person.builder()
                .id(3L)
                .firstName("FirstName3")
                .lastName("LastName3")
                .addresses(Arrays.asList(
                        Address.builder().zip(1501).city("Budapest").street("Andrássy út 5.").isPermanent(1).build(),
                        Address.builder().zip(1601).city("Budapest").street("Blaha Lujza tér 6.").isPermanent(0).build()
                ))
                .contacts(Arrays.asList(
                        Contact.builder().email("teszt5@email.com").telephone("+36 30 548 9622").build(),
                        Contact.builder().email("teszt6@email.com").telephone("+36 30 548 9623").build()
                ))
                .build());

        // Személy #4
        persons.add(Person.builder()
                .id(4L)
                .firstName("FirstName4")
                .lastName("LastName4")
                .addresses(Arrays.asList(
                        Address.builder().zip(1701).city("Budapest").street("Oktogon 7.").isPermanent(0).build(),
                        Address.builder().zip(1801).city("Budapest").street("Nyugati pályaudvar 8.").isPermanent(1).build()
                ))
                .contacts(Arrays.asList(
                        Contact.builder().email("teszt7@email.com").telephone("+36 30 548 9624").build(),
                        Contact.builder().email("teszt8@email.com").telephone("+36 30 548 9625").build()
                ))
                .build());

        // Személy #5
        persons.add(Person.builder()
                .id(5L)
                .firstName("FirstName5")
                .lastName("LastName5")
                .addresses(Arrays.asList(
                        Address.builder().zip(1901).city("Budapest").street("Keleti pályaudvar 9.").isPermanent(1).build(),
                        Address.builder().zip(2001).city("Budapest").street("Széchenyi fürdő 10.").isPermanent(0).build()
                ))
                .contacts(Arrays.asList(
                        Contact.builder().email("teszt9@email.com").telephone("+36 30 548 9626").build(),
                        Contact.builder().email("teszt10@email.com").telephone("+36 30 548 9627").build()
                ))
                .build());
    }

    @Test
    public void PersonController_GetPersons_ReturnPersons() throws Exception {
        // Arrange
        when(personService.getPersons()).thenReturn(persons);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/persons"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(persons.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value("Jane"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName").value("Doe"));
    }

    @Test
    public void PersonController_CreatePerson_ReturnPerson() throws Exception {
        //Act
        given(personService.createPerson(any(Person.class))).willReturn(persons.get(0));

        ResultActions response = mockMvc.perform(
                post("http://localhost:8080/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(persons.get(0))));
        //Assert
        response.andExpect(MockMvcResultMatchers.status().isCreated());
        response.andExpect(MockMvcResultMatchers.content().json("{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"addresses\":[{\"id\":null,\"zip\":1045,\"city\":\"Budapest\",\"street\":\"Petőfi utca 5.\",\"isPermanent\":0}],\"contacts\":[{\"id\":null,\"email\":\"teszt@email.com\",\"telephone\":\"+36 30 548 9618\"}]}"));

    }
}
