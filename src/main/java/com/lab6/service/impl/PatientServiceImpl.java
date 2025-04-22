package com.lab6.service.impl;

import com.lab6.dto.mapper.AddressMapper;
import com.lab6.dto.mapper.PatientMapper;
import com.lab6.dto.request.PatientRequest;
import com.lab6.dto.response.PatientResponse;
import com.lab6.entity.Patient;
import com.lab6.exception.PatientNotFoundException;
import com.lab6.repository.PatientRepository;
import com.lab6.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Override
    public List<PatientResponse> getAll() {
        return patientRepository.findAll().stream()
                .map(PatientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PatientResponse getById(Long id) {
        return patientRepository.findById(id)
                .map(PatientMapper::toResponse)
                .orElseThrow(() -> new PatientNotFoundException(id));
    }

    @Override
    public PatientResponse create(PatientRequest request) {
        return PatientMapper.toResponse(patientRepository.save(PatientMapper.toEntity(request)));
    }

    @Override
    public PatientResponse update(Long id, PatientRequest request) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));

        patient.setFullName(request.getFullName());
        patient.setAddress(AddressMapper.toEntity(request.getAddress()));

        return PatientMapper.toResponse(patientRepository.save(patient));
    }

    @Override
    public void delete(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new PatientNotFoundException(id);
        }
        patientRepository.deleteById(id);
    }
}
