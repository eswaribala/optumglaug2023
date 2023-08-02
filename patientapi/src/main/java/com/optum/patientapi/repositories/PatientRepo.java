package com.optum.patientapi.repositories;

import com.optum.patientapi.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient,String>, JpaSpecificationExecutor<Patient> {


    @Query("Select p from Patient p where p.opId=:OPID")
    public List<Patient> findPatientByOPID(@Param("OPID") long OPID);

}
