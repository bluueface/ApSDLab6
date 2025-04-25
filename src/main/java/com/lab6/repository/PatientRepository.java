package com.lab6.repository;

import com.lab6.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT p FROM Patient p " +
            "WHERE LOWER(p.fullName) " +
            "LIKE LOWER(CONCAT('%', :searchString, '%')) " +
            "OR LOWER(p.patientNo) " +
            "LIKE LOWER(CONCAT('%', :searchString, '%'))")
    Page<Patient> searchPatients(@Param("searchString") String searchString, Pageable pageable);
}
