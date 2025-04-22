package com.lab6.repository;

import com.lab6.entity.Address;
import com.lab6.entity.Surgery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SurgeryRepositoryTest {

    @Autowired
    private SurgeryRepository surgeryRepository;

    @Test
    void testSaveSurgery() {
        Surgery surgery = new Surgery();
        surgery.setSurgeryNo("s001");
        surgery.setAddress(new Address("Elm St", "Austin", "TX", "75001"));

        Surgery saved = surgeryRepository.save(surgery);
        assertThat(saved.getId()).isNotNull();
        assertThat(surgeryRepository.findById(saved.getId())).isPresent();
    }
}