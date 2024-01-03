package com.example.personregistry.service;

import com.example.personregistry.model.Address;
import com.example.personregistry.repository.AddressRepository;
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
        return null;
    }

    public Address createAddress(Address address) {
        return null;
    }

    public Address updateAddress(Long id, Address updatedAddress) {
        return null;
    }

    public void deleteAddress(Long id) {
        repository.deleteById(id);
    }


}
