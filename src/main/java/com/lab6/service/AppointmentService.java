package com.lab6.service;

import com.lab6.dto.request.AppointmentRequest;
import com.lab6.dto.response.AppointmentResponse;

import java.util.List;

public interface AppointmentService {
    List<AppointmentResponse> getAll();

    AppointmentResponse getById(Long id);

    AppointmentResponse create(AppointmentRequest appointment);

    AppointmentResponse update(Long id, AppointmentRequest updated);

    void delete(Long id);
}
