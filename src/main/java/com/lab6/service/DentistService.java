package com.lab6.service;

import com.lab6.dto.request.DentistRequest;
import com.lab6.dto.response.DentistResponse;

import java.util.List;

public interface DentistService {
    List<DentistResponse> getAll();

    DentistResponse getById(Long id);

    DentistResponse create(DentistRequest dentist);

    DentistResponse update(Long id, DentistRequest updated);

    void delete(Long id);
}
