package com.lab6.dto.request;

import com.lab6.dto.AddressDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequest {
    @NotBlank
    private String patientNo;

    @NotBlank
    private String fullName;

    private AddressDto address;
}