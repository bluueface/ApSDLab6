package com.lab6.service.impl;

import com.lab6.entity.Appointment;
import com.lab6.repository.AppointmentRepository;
import com.lab6.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public Appointment getById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public Appointment create(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment update(Long id, Appointment updated) {
        Appointment appointment = getById(id);
        if (appointment != null) {
            appointment.setAppointmentDate(updated.getAppointmentDate());
            appointment.setPatient(updated.getPatient());
            appointment.setDentist(updated.getDentist());
            appointment.setSurgery(updated.getSurgery());
        }
        return appointmentRepository.save(appointment);
    }

    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }
}
