package com.lab6.dto.mapper;

import com.lab6.dto.response.AppointmentResponse;
import com.lab6.entity.Appointment;

public class AppointmentMapper {
    public static AppointmentResponse toResponse(Appointment a) {
        return new AppointmentResponse(
                a.getId(),
                a.getAppointmentDate(),
                PatientMapper.toResponse(a.getPatient()),
                DentistMapper.toResponse(a.getDentist()),
                SurgeryMapper.toResponse(a.getSurgery())
        );
    }
}
