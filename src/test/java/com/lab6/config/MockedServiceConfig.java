package com.lab6.config;

import com.lab6.filter.JWTAuthFilter;
import com.lab6.service.PatientService;
import com.lab6.service.impl.DentalSurgeryUserDetailsService;
import com.lab6.utils.JWTManagementUtilityService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class MockedServiceConfig {
    @Bean
    @Primary
    public PatientService patientService() {
        return Mockito.mock(PatientService.class);
    }

    @Bean
    @Primary
    public DentalSurgeryUserDetailsService userDetailsService() {
        return Mockito.mock(DentalSurgeryUserDetailsService.class);
    }

    @Bean
    @Primary
    public JWTAuthFilter jwtAuthFilter() {
        return Mockito.mock(JWTAuthFilter.class);
    }

    @Bean
    @Primary
    public JWTManagementUtilityService jWTManagementUtilityService() {
        return Mockito.mock(JWTManagementUtilityService.class);
    }

}