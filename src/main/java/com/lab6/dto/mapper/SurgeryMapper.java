package com.lab6.dto.mapper;

import com.lab6.dto.response.SurgeryResponse;
import com.lab6.entity.Surgery;

public class SurgeryMapper {
    public static SurgeryResponse toResponse(Surgery entity) {
        return entity != null ? new SurgeryResponse(
                entity.getId(),
                entity.getSurgeryNo(),
                AddressMapper.toDto(entity.getAddress())
        ) : null;
    }
}
