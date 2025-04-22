package com.lab6.service;

import com.lab6.dto.response.AppointmentResponse;
import com.lab6.entity.Appointment;
import com.lab6.repository.AppointmentRepository;
import com.lab6.service.impl.AppointmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AppointmentServiceImplTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAppointments() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setAppointmentDate(LocalDateTime.now());

        when(appointmentRepository.findAll()).thenReturn(List.of(appointment));

        List<AppointmentResponse> responses = appointmentService.getAll();

        assertEquals(1, responses.size());
    }

    @Test
    void testGetAppointmentById() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setAppointmentDate(LocalDateTime.of(2024, 1, 1, 10, 0));

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));

        AppointmentResponse response = appointmentService.getById(1L);
        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void testGetAppointmentById_NotFound() {
        when(appointmentRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> appointmentService.getById(2L));
    }
}
