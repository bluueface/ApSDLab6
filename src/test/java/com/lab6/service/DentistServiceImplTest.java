package com.lab6.service;

import com.lab6.dto.response.DentistResponse;
import com.lab6.entity.Dentist;
import com.lab6.repository.DentistRepository;
import com.lab6.service.impl.DentistServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class DentistServiceImplTest {

    @Mock
    private DentistRepository dentistRepository;

    @InjectMocks
    private DentistServiceImpl dentistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDentists() {
        Dentist dentist = new Dentist();
        dentist.setFullName("Sarah Connor");

        when(dentistRepository.findAll()).thenReturn(List.of(dentist));

        List<DentistResponse> responses = dentistService.getAll();

        assertEquals(1, responses.size());
        assertEquals("Sarah Connor", responses.get(0).getFullName());
    }

    @Test
    void testGetDentistById() {
        Dentist dentist = new Dentist();
        dentist.setId(1L);
        dentist.setFullName("Max Payne");

        when(dentistRepository.findById(1L)).thenReturn(Optional.of(dentist));

        DentistResponse response = dentistService.getById(1L);

        assertEquals("Max Payne", response.getFullName());
    }

    @Test
    void testGetDentistById_NotFound() {
        when(dentistRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> dentistService.getById(2L));
    }
}
