package com.lab6.service.impl;

import com.lab6.dto.mapper.AppointmentMapper;
import com.lab6.dto.request.AppointmentRequest;
import com.lab6.dto.response.AppointmentResponse;
import com.lab6.entity.Appointment;
import com.lab6.exception.AppointmentNotFoundException;
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

    @Override
    public List<AppointmentResponse> getAll() {
        return appointmentRepository.findAll().stream().map(AppointmentMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public AppointmentResponse getById(Long id) {
        return appointmentRepository.findById(id)
                .map(AppointmentMapper::toResponse)
                .orElseThrow(() -> new AppointmentNotFoundException(id));
    }

    @Override
    public AppointmentResponse create(AppointmentRequest request) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(request.getDate());
        appointment.setPatient(patientRepository.findById(request.getPatientId()).orElse(null));
        appointment.setDentist(dentistRepository.findById(request.getDentistId()).orElse(null));
        appointment.setSurgery(surgeryRepository.findById(request.getSurgeryId()).orElse(null));
        return AppointmentMapper.toResponse(appointmentRepository.save(appointment));
    }

    @Override
    public AppointmentResponse update(Long id, AppointmentRequest request) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException(id));
        if (appointment != null) {
            appointment.setAppointmentDate(request.getDate());
            appointment.setPatient(patientRepository.findById(request.getPatientId()).orElse(null));
            appointment.setDentist(dentistRepository.findById(request.getDentistId()).orElse(null));
            appointment.setSurgery(surgeryRepository.findById(request.getSurgeryId()).orElse(null));
        }
        return AppointmentMapper.toResponse(appointmentRepository.save(appointment));
    }

    @Override
    public void delete(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new AppointmentNotFoundException(id);
        }
        appointmentRepository.deleteById(id);
    }
}
