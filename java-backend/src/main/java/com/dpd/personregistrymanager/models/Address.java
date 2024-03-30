package com.dpd.personregistrymanager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int zipCode;

    private String city;

    private String street;

    private int houseNumber;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "person_id")
    private Person person;


    @Override
    public String toString() {
        return String.format("Cím: %d, %s, %s - %d %n", zipCode, city, street, houseNumber);
    }

}