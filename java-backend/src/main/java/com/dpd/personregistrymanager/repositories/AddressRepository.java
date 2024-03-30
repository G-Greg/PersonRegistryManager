package com.dpd.personregistrymanager.repositories;

import com.dpd.personregistrymanager.models.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long> {

    List<Address> findAll();
}
