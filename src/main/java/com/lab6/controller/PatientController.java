package com.lab6.controller;

import com.lab6.dto.request.PatientRequest;
import com.lab6.dto.response.PatientResponse;
import com.lab6.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/adsweb/api/v1")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/patients")
    public Page<PatientResponse> getAllPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "fullName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Pageable pageable = sortDir.equalsIgnoreCase("desc")
                ? PageRequest.of(page, size, Sort.by(sortBy).descending())
                : PageRequest.of(page, size, Sort.by(sortBy).ascending());

        return patientService.getAll(pageable);
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<PatientResponse> getPatientById(@PathVariable Long id) {
        PatientResponse response = patientService.getById(id);
        return (response != null) ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @PostMapping("/patients")
    public ResponseEntity<PatientResponse> createPatient(@RequestBody PatientRequest request) {
        return ResponseEntity.ok(patientService.create(request));
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<PatientResponse> updatePatient(@PathVariable Long id, @RequestBody PatientRequest request) {
        PatientResponse response = patientService.update(id, request);
        return (response != null) ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/patients/search")
    public Page<PatientResponse> searchPatients(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "fullName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Pageable pageable = sortDir.equalsIgnoreCase("desc")
                ? PageRequest.of(page, size, Sort.by(sortBy).descending())
                : PageRequest.of(page, size, Sort.by(sortBy).ascending());

        return (Page<PatientResponse>) patientService.getAll(pageable).map(p ->
                (p.getFullName().toLowerCase().contains(query.toLowerCase()) ||
                        p.getPatientNo().toLowerCase().contains(query.toLowerCase()))
                        ? p
                        : null
        ).filter(Objects::nonNull);
    }

    @GetMapping("/addresses")
    public Page<PatientResponse> getAllAddressesSortedByCity(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Pageable pageable = sortDir.equalsIgnoreCase("desc")
                ? PageRequest.of(page, size, Sort.by("address.city").descending())
                : PageRequest.of(page, size, Sort.by("address.city").ascending());

        return patientService.getAll(pageable);
    }
}