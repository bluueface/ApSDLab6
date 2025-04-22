package com.lab6.service;

import com.lab6.dto.request.PatientRequest;
import com.lab6.dto.response.PatientResponse;

import java.util.List;


public interface PatientService {
    List<PatientResponse> getAll();

    PatientResponse getById(Long id);

    PatientResponse create(PatientRequest patient);

    PatientResponse update(Long id, PatientRequest updated);

    void delete(Long id);
}
