package com.example.personregistry.model;

import com.example.personregistry.exception.AddressException;
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

        if (addresses.size() == 1) {
            this.addresses = addresses;
        } else if (addresses.size() == 2) {

            if (addresses.get(0).getIsPermanent() == 0 && addresses.get(1).getIsPermanent() == 0)
                throw new AddressException("Egyszerre csak egy ideiglenes címmel rendelkezhet!");

            else if (addresses.get(0).getIsPermanent() == 1 && addresses.get(1).getIsPermanent() == 1)
                throw new AddressException("Egyszerre csak egy állandó címmel rendelkezhet!");

            else
                this.addresses = addresses;
        } else {
            throw new AddressException("Egy személyhez legfeljebb két cím tartozhat!");
        }
    }

    @Override
    public String toString() {
        var pString = String.format("Személy: %s %s %n", lastName, firstName);

        var aString = "";
        for (Address address : addresses) {
            aString = aString.concat(address.toString());
        }

        var cString = "";
        for (Contact contact : contacts) {
            cString = cString.concat(contact.toString());
        }

        return pString + aString + cString;
    }
}
