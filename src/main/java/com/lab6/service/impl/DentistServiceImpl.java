package com.lab6.service.impl;

import com.lab6.entity.Dentist;
import com.lab6.repository.DentistRepository;
import com.lab6.service.DentistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistServiceImpl implements DentistService {
    private final DentistRepository dentistRepository;

    public DentistServiceImpl(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    public List<Dentist> getAll() {
        return dentistRepository.findAll();
    }

    public Dentist getById(Long id) {
        return dentistRepository.findById(id).orElse(null);
    }

    public Dentist create(Dentist dentist) {
        return dentistRepository.save(dentist);
    }

    public Dentist update(Long id, Dentist updated) {
        Dentist dentist = getById(id);
        if (dentist != null) dentist.setFullName(updated.getFullName());
        return dentistRepository.save(dentist);
    }

    public void delete(Long id) {
        dentistRepository.deleteById(id);
    }
}
