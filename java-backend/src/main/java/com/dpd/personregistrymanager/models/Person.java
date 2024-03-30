package com.dpd.personregistrymanager.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "persons")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String birthdate;

    private String birthplace;

    private int TAJ;

    private int taxId;

    private String email;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Address> addresses;

    @ElementCollection
    private List<String> phoneNumbers;

}
