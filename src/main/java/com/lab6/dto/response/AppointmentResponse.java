package com.lab6.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponse {
    private Long id;
    private LocalDateTime date;
    private PatientResponse patient;
    private DentistResponse dentist;
    private SurgeryResponse surgery;
}
