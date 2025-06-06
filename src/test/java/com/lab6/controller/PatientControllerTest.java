package com.lab6.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab6.config.MockedServiceConfig;
import com.lab6.dto.request.PatientRequest;
import com.lab6.dto.response.PatientResponse;
import com.lab6.service.PatientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(MockedServiceConfig.class)
@WithMockUser(username = "admin", roles = {"ADMIN"})
public class PatientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllPatients() throws Exception {
        PatientResponse response = new PatientResponse();
        response.setFullName("John Doe");

        Page<PatientResponse> responsePage = new PageImpl<>(Collections.singletonList(response));

        Mockito.when(patientService.getAll(Mockito.any(Pageable.class))).thenReturn(responsePage);

        mockMvc.perform(get("/adsweb/api/v1/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].fullName", is("John Doe")));

    }

    @Test
    void testGetPatientById() throws Exception {
        PatientResponse response = new PatientResponse();
        response.setFullName("Jane Smith");

        Mockito.when(patientService.getById(1L)).thenReturn(response);

        mockMvc.perform(get("/adsweb/api/v1/patients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName", is("Jane Smith")));
    }

    @Test
    void testCreatePatient() throws Exception {
        PatientRequest request = new PatientRequest();
        request.setFullName("Alan Walker");
        request.setPatientNo("P123");

        PatientResponse response = new PatientResponse();
        response.setFullName("Alan Walker");

        Mockito.when(patientService.create(any())).thenReturn(response);

        mockMvc.perform(post("/adsweb/api/v1/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName", is("Alan Walker")));
    }

    @Test
    void testUpdatePatient() throws Exception {
        PatientRequest request = new PatientRequest();
        request.setFullName("Update Test");

        PatientResponse response = new PatientResponse();
        response.setFullName("Update Test");

        Mockito.when(patientService.update(anyLong(), any())).thenReturn(response);

        mockMvc.perform(put("/adsweb/api/v1/patients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName", is("Update Test")));
    }

    @Test
    void testDeletePatient() throws Exception {
        mockMvc.perform(delete("/adsweb/api/v1/patients/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testSearchPatient() throws Exception {
        PatientResponse response = new PatientResponse();
        response.setFullName("Search Match");
        response.setPatientNo("P123");

        Page<PatientResponse> responsePage = new PageImpl<>(Collections.singletonList(response));

        Mockito.when(patientService.searchPatients(Mockito.eq("search"), Mockito.any(Pageable.class)))
                .thenReturn(responsePage);

        mockMvc.perform(get("/adsweb/api/v1/patients/search/search"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].fullName", is("Search Match")));
    }

}
