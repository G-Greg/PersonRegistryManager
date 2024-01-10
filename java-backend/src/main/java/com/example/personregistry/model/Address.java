package com.example.personregistry.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Addresses")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "person_id")
    private Person person;

    private int zip;

    private String city;

    private String street;

    @Column(name = "is_permanent")
    private int isPermanent;

    @Override
    public String toString() {
        var type = (isPermanent == 1) ? "állandó" : "ideiglenes";
        return String.format("Cím: %d, %s, %s - %s %n", zip, city, street, type);
    }
}