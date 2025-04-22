package com.lab6.repository;

import com.lab6.entity.Address;
import com.lab6.entity.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    @DisplayName("Should save and retrieve patient")
    void testSaveAndFindPatient() {
        Patient patient = new Patient();
        patient.setFullName("Alice Johnson");
        patient.setPatientNo("P1001");
        patient.setAddress(new Address("Main St", "Dallas", "TX", "75001"));

        Patient saved = patientRepository.save(patient);

        Optional<Patient> result = patientRepository.findById(saved.getId());

        assertThat(result).isPresent();
        assertThat(result.get().getFullName()).isEqualTo("Alice Johnson");
    }

    @Test
    @DisplayName("Should find all patients")
    void testFindAllPatients() {

        Patient patient1 = new Patient();
        patient1.setFullName("John Smith");
        patient1.setPatientNo("P2001");
        patient1.setAddress(new Address("Elm St", "Austin", "TX", "75001"));

        Patient patient2 = new Patient();
        patient2.setFullName("Laura Brown");
        patient2.setPatientNo("P2002");
        patient2.setAddress(new Address("Pine", "Houston", "TX", "77001"));

        patientRepository.save(patient1);
        patientRepository.save(patient2);

        List<Patient> all = patientRepository.findAll();
        assertThat(all).hasSize(2);
    }
}
