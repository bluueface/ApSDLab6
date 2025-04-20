package com.lab6.service.impl;

import com.lab6.dto.mapper.DentistMapper;
import com.lab6.dto.request.DentistRequest;
import com.lab6.dto.response.DentistResponse;
import com.lab6.entity.Dentist;
import com.lab6.repository.DentistRepository;
import com.lab6.service.DentistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DentistServiceImpl implements DentistService {
    private final DentistRepository dentistRepository;

    public DentistServiceImpl(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    @Override
    public List<DentistResponse> getAll() {
        return dentistRepository.findAll().stream()
                .map(DentistMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DentistResponse getById(Long id) {
        return dentistRepository.findById(id)
                .map(DentistMapper::toResponse)
                .orElse(null);
    }

    @Override
    public DentistResponse create(DentistRequest request) {
        return DentistMapper.toResponse(dentistRepository.save(DentistMapper.toEntity(request)));
    }

    @Override
    public DentistResponse update(Long id, DentistRequest request) {
        Dentist dentist = dentistRepository.findById(id).orElse(null);
        if (dentist != null) dentist.setFullName(request.getFullName());
        return DentistMapper.toResponse(dentistRepository.save(dentist));
    }

    @Override
    public void delete(Long id) {
        dentistRepository.deleteById(id);
    }
}
