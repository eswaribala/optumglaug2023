package com.optum.patientapi.queries;

import com.intuit.graphql.filter.client.ExpressionFormat;
import com.intuit.graphql.filter.client.FilterExpression;
import com.optum.patientapi.dtos.FilterField;
import com.optum.patientapi.dtos.GenderFilterField;
import com.optum.patientapi.dtos.PatientFilter;
import com.optum.patientapi.models.Patient;
import com.optum.patientapi.repositories.PatientRepo;
import com.optum.patientapi.services.PatientService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.xml.crypto.Data;
import java.util.List;

@Component
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

    public List<Patient> findPatientWithFilter(DataFetchingEnvironment env){

        Specification<Patient> spec=getSpecification(env);
       /* if(patientFilter.getOpId()!=null){

          spec=byOPId(patientFilter.getOpId());
        }

        if(patientFilter.getEmail()!=null){

            spec=byEmail(patientFilter.getEmail());
        }

        if(patientFilter.getGender()!=null){

            spec=byGender(patientFilter.getGender());
        }
*/
        if(spec!=null)
           return this.patientRepo.findAll(spec);
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


    private Specification<Patient> getSpecification(DataFetchingEnvironment env) {
        FilterExpression.FilterExpressionBuilder builder = FilterExpression.newFilterExpressionBuilder();
        FilterExpression filterExpression = builder.field(env.getField())
                .args(env.getArguments())
                .build();
        Specification<Patient> specification = filterExpression.getExpression(ExpressionFormat.JPA);
        return specification;
    }
}
