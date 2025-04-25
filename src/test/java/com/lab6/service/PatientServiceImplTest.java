package com.lab6.service;

import com.lab6.dto.response.PatientResponse;
import com.lab6.entity.Address;
import com.lab6.entity.Patient;
import com.lab6.repository.PatientRepository;
import com.lab6.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        Patient patient = new Patient();
        patient.setFullName("John Doe");
        patient.setPatientNo("P123");
        patient.setAddress(new Address("123 Main St", "Fairfield", "IA", "52557"));

        Page<Patient> patientPage = new PageImpl<>(Collections.singletonList(patient));

        when(patientRepository.findAll(any(Pageable.class))).thenReturn(patientPage);

        Page<PatientResponse> responses = patientService.getAll(Pageable.unpaged());

        assertNotNull(responses);
        assertEquals(1, responses.getTotalElements());
        assertEquals("John Doe", responses.getContent().get(0).getFullName());
    }

    @Test
    void testGetPatientById_Success() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setFullName("Alice Smith");
        patient.setAddress(new Address("123 Main St", "Fairfield", "IA", "52557"));

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        PatientResponse response = patientService.getById(1L);
        assertNotNull(response);
        assertEquals("Alice Smith", response.getFullName());
    }

    @Test
    void testGetPatientById_NotFound() {
        when(patientRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> patientService.getById(2L));
    }
}
