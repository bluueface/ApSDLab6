package com.lab6.service;

import com.lab6.entity.Dentist;

import java.util.List;

public interface DentistService {
    List<Dentist> getAll();

    Dentist getById(Long id);

    Dentist create(Dentist dentist);

    Dentist update(Long id, Dentist updated);

    void delete(Long id);
}
