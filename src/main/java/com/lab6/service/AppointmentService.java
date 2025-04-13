package com.lab6.service;

import com.lab6.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAll();

    Appointment getById(Long id);

    Appointment create(Appointment appointment);

    Appointment update(Long id, Appointment updated);

    void delete(Long id);
}
