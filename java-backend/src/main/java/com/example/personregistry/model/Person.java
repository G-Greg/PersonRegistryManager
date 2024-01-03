package com.example.personregistry.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Persons")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Address> addresses;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Contact> contacts;



    public void setAddresses(List<Address> addresses) {
        if (addresses == null) {
            addresses = new ArrayList<>();
        }

        if (addresses.size() < 2) {
            this.addresses = addresses;
        } else {
            throw new RuntimeException("Egy személyhez legfeljebb két cím tartozhat!");
        }
    }
}
