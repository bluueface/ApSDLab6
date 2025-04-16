package com.lab6.controller;

import com.lab6.entity.Patient;
import com.lab6.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/adsweb/api/v1")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientService.getAll().stream()
                .sorted(Comparator.comparing(p -> p.getFullName().split(" ")[1])) // Assuming format "First Last"
                .collect(Collectors.toList());
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getById(id);
        if (patient == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(patient);
    }

    @PostMapping("/patients")
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.create(patient);
    }

    @PutMapping("/patient/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient updated) {
        Patient patient = patientService.update(id, updated);
        if (patient == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(patient);
    }

    @DeleteMapping("/patient/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/patient/search/{searchString}")
    public List<Patient> searchPatient(@PathVariable String searchString) {
        return patientService.getAll().stream()
                .filter(p -> p.getFullName().toLowerCase().contains(searchString.toLowerCase()) ||
                        p.getPatientNo().toLowerCase().contains(searchString.toLowerCase()))
                .collect(Collectors.toList());
    }

    @GetMapping("/addresses")
    public List<Patient> getAllAddressesSortedByCity() {
        return patientService.getAll().stream()
                .sorted(Comparator.comparing(p -> p.getAddress().getCity()))
                .collect(Collectors.toList());
    }
}