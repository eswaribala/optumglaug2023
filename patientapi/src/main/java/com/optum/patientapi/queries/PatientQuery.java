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

        Specification<Patient> spec=null;

        if((patientFilter.getOr()!=null)&&(patientFilter.getOr().size() > 0)) {
                log.info("Or Condition Present");
           for(int i=0;i<patientFilter.getOr().size();i++) {

               log.info(patientFilter.getOr().get(i).getOpId().toString());
               log.info(patientFilter.getOr().get(i).getEmail().toString());
               if ((patientFilter.getOr().get(i).getOpId() != null) || (patientFilter.getOr().get(i).getEmail() != null)) {
                   log.info(patientFilter.getOr().get(i).getOpId().toString());
                   log.info(patientFilter.getOr().get(i).getEmail().toString());
                   if(spec!=null)
                      spec = spec.or(Specification.where(byOPId(patientFilter.getOr().get(i).getOpId()))
                           .or(byEmail(patientFilter.getOr().get(i).getEmail())));
                   else
                       spec=Specification.where(byOPId(patientFilter.getOr().get(i).getOpId()))
                               .or(byEmail(patientFilter.getOr().get(i).getEmail()));


               }
           }
            return this.patientRepo.findAll(spec);
        }


        if((patientFilter.getAnd()!=null)&&(patientFilter.getAnd().size() > 0)) {
            log.info("And Condition Present");
            for(int i=0;i<patientFilter.getAnd().size();i++) {

                log.info(patientFilter.getAnd().get(i).getOpId().toString());
                log.info(patientFilter.getAnd().get(i).getEmail().toString());
                if ((patientFilter.getAnd().get(i).getOpId() != null) || (patientFilter.getAnd().get(i).getEmail() != null)) {
                    log.info(patientFilter.getAnd().get(i).getOpId().toString());
                    log.info(patientFilter.getAnd().get(i).getEmail().toString());
                    if(spec!=null)
                        spec = spec.or(Specification.where(byOPId(patientFilter.getAnd().get(i).getOpId()))
                                .and(byEmail(patientFilter.getAnd().get(i).getEmail())));
                    else
                        spec=Specification.where(byOPId(patientFilter.getAnd().get(i).getOpId()))
                                .and(byEmail(patientFilter.getAnd().get(i).getEmail()));


                }
            }
            return this.patientRepo.findAll(spec);
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
