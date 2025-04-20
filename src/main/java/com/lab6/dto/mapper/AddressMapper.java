package com.lab6.dto.mapper;

import com.lab6.dto.AddressDto;
import com.lab6.entity.Address;

public class AddressMapper {
    public static Address toEntity(AddressDto dto) {
        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setZipCode(dto.getZipCode());
        return address;
    }

    public static AddressDto toDto(Address entity) {
        return new AddressDto(
                entity.getStreet(),
                entity.getCity(),
                entity.getState(),
                entity.getZipCode()
        );
    }
}
