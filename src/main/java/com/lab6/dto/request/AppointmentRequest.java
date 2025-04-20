package com.lab6.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequest {
    private LocalDateTime date;
    private Long patientId;
    private Long dentistId;
    private Long surgeryId;
}
