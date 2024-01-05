package com.example.personregistry.service;

import com.example.personregistry.model.Address;
import com.example.personregistry.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    public List<Address> getAddresses() {
        return repository.findAll();
    }

    public Address getAddressById(Long id) {
        var optional = repository.findById(id);
        if (optional.isPresent())
            return optional.get();
        return null;
    }

    public Address createAddress(Address address) {
        //akkor adjuk hozzá ha nem több mint 2
        //és csak 1 áll. és 1 ideig.
        return repository.save(address);
    }

    public Address updateAddress(Long id, Address updatedAddress) {
        //akkor adjuk hozzá ha nem több mint 2
        //és csak 1 áll. és 1 ideig.
        var optional = repository.findById(id);

        if (optional.isPresent()) {
            Address existingContact = optional.get();

            existingContact.setZip(updatedAddress.getZip());
            existingContact.setCity(updatedAddress.getCity());
            existingContact.setStreet(updatedAddress.getStreet());
            existingContact.setIsPermanent(updatedAddress.getIsPermanent());

            return repository.save(existingContact);
        } else {
            throw new EntityNotFoundException("A cím nem található! id:" + id);
        }
    }

    public void deleteAddress(Long id) {
        repository.deleteById(id);
    }
}
