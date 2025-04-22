package com.lab6.repository;

import com.lab6.entity.Dentist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DentistRepositoryTest {

    @Autowired
    private DentistRepository dentistRepository;

    @Test
    void testSaveAndFindDentist() {
        Dentist dentist = new Dentist();
        dentist.setFullName("Greg House");

        Dentist saved = dentistRepository.save(dentist);
        assertThat(saved.getId()).isNotNull();
        assertThat(dentistRepository.findById(saved.getId())).isPresent();
    }
}
