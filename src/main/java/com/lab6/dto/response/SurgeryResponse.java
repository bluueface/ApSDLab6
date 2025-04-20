package com.lab6.dto.response;

import com.lab6.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurgeryResponse {
    private Long id;
    private String surgeryNo;
    private AddressDto address;
}
