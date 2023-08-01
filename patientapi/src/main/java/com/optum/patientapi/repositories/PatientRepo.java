package com.optum.patientapi.repositories;

import com.optum.patientapi.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient,Long> {
}
