package com.lab6.dto.response;

import com.lab6.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponse {
    private Long id;
    private String patientNo;
    private String fullName;
    private AddressDto address;
}
