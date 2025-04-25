package com.lab6.service;

import com.lab6.dto.request.PatientRequest;
import com.lab6.dto.response.PatientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PatientService {
    Page<PatientResponse> getAll(Pageable pageable);

    Page<PatientResponse> searchPatients(String searchString, Pageable pageable);

    PatientResponse getById(Long id);

    PatientResponse create(PatientRequest patient);

    PatientResponse update(Long id, PatientRequest updated);

    void delete(Long id);
}
