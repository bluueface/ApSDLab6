package com.lab6.repository;

import com.lab6.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void testSaveAddress() {
        Address address = new Address("1st St", "New York", "NY", "10001");
        Address saved = addressRepository.save(address);
        assertThat(saved.getId()).isNotNull();
        assertThat(addressRepository.findById(saved.getId())).isPresent();
    }
}
