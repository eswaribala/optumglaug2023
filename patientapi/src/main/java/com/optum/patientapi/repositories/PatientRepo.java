package com.optum.patientapi.repositories;

import com.optum.patientapi.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient,String> {


    @Query("Select p from Patient p where p.opId=:opId")
    public List<Patient> findPatientByOPID(@Param("OPID") long opId);
}
