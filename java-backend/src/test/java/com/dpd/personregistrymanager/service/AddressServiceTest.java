package com.dpd.personregistrymanager.service;

import com.dpd.personregistrymanager.models.Address;
import com.dpd.personregistrymanager.models.Person;
import com.dpd.personregistrymanager.repositories.AddressRepository;
import com.dpd.personregistrymanager.repositories.PersonRepository;
import com.dpd.personregistrymanager.services.AddressService;
import com.dpd.personregistrymanager.services.PersonService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private static Address address1;
    private static Address address2;
    private static Address address3;

    @BeforeAll
    static void init() {
        address1 = Address.builder()
                .id(1L)
                .zipCode(1040)
                .city("Budapest")
                .street("Petőfi utca")
                .houseNumber(9)
                .build();

        address2 = Address.builder()
                .id(2L)
                .zipCode(1028)
                .city("Budapest")
                .street("Alma utca")
                .houseNumber(19)
                .build();

        address3 = Address.builder()
                .id(3L)
                .zipCode(1140)
                .city("Budapest")
                .street("Körte utca")
                .houseNumber(4)
                .build();
    }

    @Test
    void findAllAddress() {
        when(addressRepository.findAll()).thenReturn(Arrays.asList(address1, address2, address3));
        List<Address> foundAddress = addressService.getAddresses();
        assertThat(foundAddress).isNotNull();
        assertThat(foundAddress.size()).isEqualTo(3);
    }

    @Test
    void findAddressById() {
        when(addressRepository.findById(1L)).thenReturn(java.util.Optional.of(address1));
        Address foundAddress = addressService.getAddress(1L);
        assertThat(foundAddress).isNotNull();
        assertThat(foundAddress).isEqualTo(address1);
    }


}
