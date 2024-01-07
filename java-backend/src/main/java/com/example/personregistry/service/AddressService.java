package com.example.personregistry.service;

import com.example.personregistry.model.Address;
import com.example.personregistry.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository repository;

    @Autowired
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }


    public List<Address> getAddresses() {
        return repository.findAll();
    }

    public Address getAddressById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("A keresett cím nem található! id:" + id));
    }

    public Address updateAddress(Long id, Address updatedAddress) {
        var existingAddress = getAddressById(id);

        existingAddress.setZip(updatedAddress.getZip());
        existingAddress.setCity(updatedAddress.getCity());
        existingAddress.setStreet(updatedAddress.getStreet());
        existingAddress.setIsPermanent(updatedAddress.getIsPermanent());

        return repository.save(existingAddress);
    }

    public void deleteAddress(Long id) {
        repository.deleteById(id);
    }
}
