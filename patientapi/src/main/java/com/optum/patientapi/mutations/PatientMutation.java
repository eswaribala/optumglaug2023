package com.optum.patientapi.mutations;

import com.optum.patientapi.dtos.PatientInput;
import com.optum.patientapi.models.FullName;
import com.optum.patientapi.models.Patient;
import com.optum.patientapi.services.PatientService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientMutation implements GraphQLMutationResolver {

    @Autowired
    private PatientService patientService;

    Patient addPatient(PatientInput patientInput){

        Patient patient=Patient.builder()
                .adharCardNo(patientInput.getAdharCardNo())
                .name(FullName.builder()
                        .firstName(patientInput.getName().getFirstName())
                        .lastName(patientInput.getName().getLastName())
                        .middleName(patientInput.getName().getMiddleName())
                        .build())
                .dob(patientInput.getDob())
                .mobileNo(patientInput.getMobileNo())
                .email(patientInput.getEmail())
                .gender(patientInput.getGender())
                .opId(patientInput.getOpId())
                .build();
        return this.patientService.addPatient(patient);

    }
    public Patient updatePatient(String adharCardNo, long mobileNo){

        return this.patientService.updatePatient(adharCardNo,mobileNo);
    }
    public boolean deletePatient(String adharCardNo){

        return this.patientService.deletePatient(adharCardNo);
    }

}
