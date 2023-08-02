package com.optum.patientapi.controllers;


import com.optum.patientapi.dtos.ResponseWrapper;
import com.optum.patientapi.models.Patient;
import com.optum.patientapi.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping({"/v1.0/{adharCardNo}"})

    public ResponseEntity<ResponseWrapper> findPatientByAdharCardNo(@PathVariable("adharCardNo") String adharCardNo){
        Patient patient=this.patientService.getPatientById(adharCardNo);

        if(patient!=null){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(patient));

        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("Patient Not Found"));
    }
}
