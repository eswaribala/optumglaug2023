package com.optum.patientapi.queries;

import com.optum.patientapi.dtos.FilterField;
import com.optum.patientapi.dtos.GenderFilterField;
import com.optum.patientapi.dtos.PatientFilter;
import com.optum.patientapi.models.Patient;
import com.optum.patientapi.repositories.PatientRepo;
import com.optum.patientapi.services.PatientService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.List;

@Component
@Slf4j
public class PatientQuery implements GraphQLQueryResolver {


    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepo patientRepo;

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

    public List<Patient> findPatientWithFilter(PatientFilter patientFilter){

        //work around
        Specification<Patient> spec1=null;
        Specification<Patient> spec2=null;
        if(patientFilter.getOr()!=null) {
            if (patientFilter.getOr().size() > 0) {
                log.info("Or Condition Present");

                if(patientFilter.getOpId()!=null){

                    spec1=byOPId(patientFilter.getOpId());
                }

                if(patientFilter.getEmail()!=null){

                    spec2=byEmail(patientFilter.getEmail());
                }

                if((spec1!=null)&&(spec2!=null))
                    return this.patientRepo.findAll(spec1.or(spec2));
                else
                    return this.patientRepo.findAll();

            }
            else
                return this.patientRepo.findAll();
        }
        else
            return this.patientRepo.findAll();




    }


    private Specification<Patient> byOPId(FilterField filterField) {
        return (Specification<Patient>) (root, query, builder) -> filterField.generateCriteria(builder, root.get("opId"));
    }

    private Specification<Patient> byEmail(FilterField filterField) {
        return (Specification<Patient>) (root, query, builder) -> filterField.generateCriteria(builder, root.get("email"));

    }

    private Specification<Patient> byGender(GenderFilterField genderFilterField) {
        return (Specification<Patient>) (root, query, builder) -> genderFilterField.generateCriteria(builder, root.get("gender"));
    }



}
