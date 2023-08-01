package com.optum.patientapi.queries;

import com.optum.patientapi.models.Patient;
import com.optum.patientapi.services.PatientService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.List;

@Component
public class PatientQuery implements GraphQLQueryResolver {


    @Autowired
    private PatientService patientService;

    public List<Patient> findAllPatients(){
        return this.patientService.getAllPatients();

    }
    public Patient findPatientByAdharCardNo(String adharCardNo){
        return this.patientService.getPatientById(adharCardNo);
    }
    public List<Patient> findPatientByOPID(long opId){
         return this.patientService.getPatientByOPID(opId);
    }
    public List<Patient> findPatientByFirstName(String firstName){

        return this.patientService.getPatientByFirstName(firstName);
    }
}
