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

    @Autowired
    private PersonService personService;


    public List<Address> getAddresses() {
        return repository.findAll();
    }

    public Address getAddressById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("A keresett cím nem található! id:" + id));
    }

    public Address addAddressTo(Long id, Address address) {
        var person = personService.getPersonById(id);

        if (person.getAddresses().size() < 2) {
            checkAddressIsValid(id, address);
            return repository.save(address);

        } else {
            throw new RuntimeException("Nem rendelkezhet kettőnél több lakcímmel!");
        }
    }

    public Address updateAddress(Long id, Address updatedAddress) {
        checkAddressIsValid(id, updatedAddress);

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

    private void checkAddressIsValid(Long id, Address address) {
        var person = personService.getPersonById(id);

        if (person.getAddresses().get(0).getIsPermanent() == 1 && address.getIsPermanent() == 1) {
            throw new RuntimeException("Nem rendelkezhet kettő állandó lakcímmel!");

        } else if (person.getAddresses().get(0).getIsPermanent() == 0 && address.getIsPermanent() == 0)
            throw new RuntimeException("Nem rendelkezhet kettő ideiglenes lakcímmel!");

    }
}
