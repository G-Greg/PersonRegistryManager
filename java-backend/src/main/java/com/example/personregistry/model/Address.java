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
        if (isPermanent == 1)
            return String.format("Cím: %d, %s, %s - állandó %n", zip, city, street);
        return String.format("Cím: %d, %s, %s - ideiglenes %n", zip, city, street);
    }
}