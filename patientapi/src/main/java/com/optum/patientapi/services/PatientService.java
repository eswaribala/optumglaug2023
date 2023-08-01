package com.optum.patientapi.services;

import com.optum.patientapi.models.Patient;
import com.optum.patientapi.repositories.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class PatientService {


    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private EntityManager entityManager;

    //insert

    public Patient addPatient(Patient patient){

        return this.patientRepo.save(patient);
    }

    //getall

    public List<Patient> getAllPatients(){
        return this.patientRepo.findAll();
    }

    public Patient getPatientById(String adharCardNo){
        return this.patientRepo.findById(adharCardNo).orElse(null);
    }

    //fetch by non primary key
    public List<Patient> getPatientByOPID(long opId){
        return this.patientRepo.findPatientByOPID(opId);
    }

    //fetch by non primary key
    public List<Patient> getPatientByFirstName(String firstName){
        CriteriaBuilder cb= entityManager.getCriteriaBuilder();
        AbstractQuery<Patient> cq=cb.createQuery(Patient.class);

        Root<Patient> patientObject=cq.from(Patient.class);
        cq.where(cb.equal(patientObject.get("name").<String> get("firstName") ,firstName));

        CriteriaQuery<Patient> selectResult=((CriteriaQuery<Patient>)cq).select(patientObject);
        TypedQuery<Patient> tq=entityManager.createQuery(selectResult);
        return tq.getResultList();
    }


    //update

    public Patient updatePatient(String adharCardNo, long mobileNo){

        Patient patient=this.getPatientById(adharCardNo);
        if(patient!=null){
            patient.setMobileNo(mobileNo);
            this.patientRepo.save(patient);
            return patient;
        }
        else
            return null;


    }

    //delete

    public boolean deletePatient(String adharCardNo){
        boolean status=false;
        Patient patient=this.getPatientById(adharCardNo);
        if(patient!=null){
            this.patientRepo.deleteById(adharCardNo);
            status=true;

        }
        return status;

    }


}
