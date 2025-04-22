package com.lab6.dto.mapper;

import com.lab6.dto.request.PatientRequest;
import com.lab6.dto.response.PatientResponse;
import com.lab6.entity.Patient;

public class PatientMapper {
    public static Patient toEntity(PatientRequest dto) {
        Patient patient = new Patient();
        patient.setPatientNo(dto.getPatientNo());
        patient.setFullName(dto.getFullName());
        patient.setAddress(AddressMapper.toEntity(dto.getAddress()));
        return patient;
    }

    public static PatientResponse toResponse(Patient entity) {
        return entity != null ? new PatientResponse(
                entity.getId(),
                entity.getPatientNo(),
                entity.getFullName(),
                AddressMapper.toDto(entity.getAddress())
        ) : null;
    }
}
