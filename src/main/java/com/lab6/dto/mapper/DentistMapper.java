package com.lab6.dto.mapper;

import com.lab6.dto.request.DentistRequest;
import com.lab6.dto.response.DentistResponse;
import com.lab6.entity.Dentist;

public class DentistMapper {
    public static Dentist toEntity(DentistRequest dto) {
        Dentist dentist = new Dentist();
        dentist.setFullName(dto.getFullName());
        return dentist;
    }

    public static DentistResponse toResponse(Dentist entity) {
        return entity != null ? new DentistResponse(entity.getId(), entity.getFullName()) : null;
    }
}