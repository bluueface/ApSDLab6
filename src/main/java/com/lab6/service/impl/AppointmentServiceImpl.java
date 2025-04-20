package com.lab6.service.impl;

import com.lab6.dto.mapper.AppointmentMapper;
import com.lab6.dto.request.AppointmentRequest;
import com.lab6.dto.response.AppointmentResponse;
import com.lab6.entity.Appointment;
import com.lab6.repository.AppointmentRepository;
import com.lab6.repository.DentistRepository;
import com.lab6.repository.PatientRepository;
import com.lab6.repository.SurgeryRepository;
import com.lab6.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DentistRepository dentistRepository;
    private final SurgeryRepository surgeryRepository;

    public List<AppointmentResponse> getAll() {
        return appointmentRepository.findAll().stream().map(AppointmentMapper::toResponse).collect(Collectors.toList());
    }

    public AppointmentResponse getById(Long id) {
        return appointmentRepository.findById(id).map(AppointmentMapper::toResponse).orElse(null);
    }

    public AppointmentResponse create(AppointmentRequest request) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(request.getDate());
        appointment.setPatient(patientRepository.findById(request.getPatientId()).orElse(null));
        appointment.setDentist(dentistRepository.findById(request.getDentistId()).orElse(null));
        appointment.setSurgery(surgeryRepository.findById(request.getSurgeryId()).orElse(null));
        return AppointmentMapper.toResponse(appointmentRepository.save(appointment));
    }

    public AppointmentResponse update(Long id, AppointmentRequest request) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment != null) {
            appointment.setAppointmentDate(request.getDate());
            appointment.setPatient(patientRepository.findById(request.getPatientId()).orElse(null));
            appointment.setDentist(dentistRepository.findById(request.getDentistId()).orElse(null));
            appointment.setSurgery(surgeryRepository.findById(request.getSurgeryId()).orElse(null));
        }
        return AppointmentMapper.toResponse(appointmentRepository.save(appointment));
    }

    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }
}
