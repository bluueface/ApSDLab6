package com.lab6.controller;

import com.lab6.dto.request.PatientRequest;
import com.lab6.dto.response.PatientResponse;
import com.lab6.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adsweb/api/v1")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/patients")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<PatientResponse>> getAllPatients(
            @PageableDefault(page = 0, size = 10, sort = "fullName") Pageable pageable
    ) {
        Page<PatientResponse> patients = patientService.getAll(pageable);
        return ResponseEntity.ok(patients);
    }


    @GetMapping("/patients/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PatientResponse> getPatientById(@PathVariable Long id) {
        PatientResponse response = patientService.getById(id);
        return (response != null) ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @PostMapping("/patients")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PatientResponse> createPatient(@Valid @RequestBody PatientRequest request) {
        return ResponseEntity.ok(patientService.create(request));
    }

    @PutMapping("/patients/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PatientResponse> updatePatient(@PathVariable Long id, @RequestBody PatientRequest request) {
        PatientResponse response = patientService.update(id, request);
        return (response != null) ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/patients/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/patients/search/{searchString}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<PatientResponse>> searchPatient(
            @PathVariable String searchString,
            @PageableDefault(page = 0, size = 10, sort = "fullName") Pageable pageable
    ) {
        Page<PatientResponse> result = patientService.searchPatients(searchString, pageable);
        return ResponseEntity.ok(result);
    }

}