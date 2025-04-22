package com.lab6.controller;

import com.lab6.dto.request.PatientRequest;
import com.lab6.dto.response.PatientResponse;
import com.lab6.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/adsweb/api/v1")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/patients")
    public List<PatientResponse> getAllPatients() {
        return patientService.getAll().stream()
                .sorted(Comparator.comparing(p -> p.getFullName().split(" ")[1])) // sort by last name
                .collect(Collectors.toList());
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

    @GetMapping("/patients/search/{searchString}")
    public List<PatientResponse> searchPatient(@PathVariable String searchString) {
        return patientService.getAll().stream()
                .filter(p -> p.getFullName().toLowerCase().contains(searchString.toLowerCase()) ||
                        p.getPatientNo().toLowerCase().contains(searchString.toLowerCase()))
                .collect(Collectors.toList());
    }

    @GetMapping("/addresses")
    public List<PatientResponse> getAllAddressesSortedByCity() {
        return patientService.getAll().stream()
                .sorted(Comparator.comparing(p -> p.getAddress().getCity()))
                .collect(Collectors.toList());
    }
}