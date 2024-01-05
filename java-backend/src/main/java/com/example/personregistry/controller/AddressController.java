package com.example.personregistry.controller;

import com.example.personregistry.model.Address;
import com.example.personregistry.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService service;

    @GetMapping
    public ResponseEntity<List<Address>> getAddresses(){
        return ResponseEntity.ok(service.getAddresses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        Address address = service.getAddressById(id);
        return ResponseEntity.ok(address);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Address> addAddressTo(@PathVariable Long id, @RequestBody Address address) {
        Address addedAddress = service.addAddressTo(id, address);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedAddress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address address) {
        Address updatedAddress = service.updateAddress(id, address);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        service.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
