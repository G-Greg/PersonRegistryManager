package com.dpd.personregistrymanager.services;

import com.dpd.personregistrymanager.models.Address;
import com.dpd.personregistrymanager.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddress(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address updateAddress(Long id, Address address) {
        return addressRepository.save(address);
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
