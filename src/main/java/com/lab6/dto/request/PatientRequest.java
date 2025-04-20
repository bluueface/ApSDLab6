package com.lab6.dto.request;

import com.lab6.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequest {
    private String patientNo;
    private String fullName;
    private AddressDto address;
}