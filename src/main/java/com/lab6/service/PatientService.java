package com.lab6.service;

import com.lab6.entity.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getAll();

    Patient getById(Long id);

    Patient create(Patient patient);

    Patient update(Long id, Patient updated);

    void delete(Long id);
}
