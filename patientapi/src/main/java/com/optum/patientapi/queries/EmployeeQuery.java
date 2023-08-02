package com.optum.patientapi.queries;

import com.optum.patientapi.models.Employee;
import com.optum.patientapi.models.Patient;
import com.optum.patientapi.services.EmployeeService;
import com.optum.patientapi.services.PatientService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeQuery implements GraphQLQueryResolver {


    @Autowired
    private EmployeeService employeeService;

    public List<Employee> findAllEmployees(){
        return this.employeeService.getAllEmployees();

    }
    public Employee findEmployeeByAdharCardNo(String adharCardNo){
        return this.employeeService.getEmployeeById(adharCardNo);
    }

}
