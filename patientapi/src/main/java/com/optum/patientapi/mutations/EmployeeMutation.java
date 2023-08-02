package com.optum.patientapi.mutations;

import com.optum.patientapi.dtos.EmployeeInput;
import com.optum.patientapi.dtos.PatientInput;
import com.optum.patientapi.models.Employee;
import com.optum.patientapi.models.FullName;
import com.optum.patientapi.models.Patient;
import com.optum.patientapi.services.EmployeeService;
import com.optum.patientapi.services.PatientService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMutation implements GraphQLMutationResolver {

    @Autowired
    private EmployeeService employeeService;

    Employee addEmployee(EmployeeInput employeeInput){

        Employee employee=Employee.builder()
                .adharCardNo(employeeInput.getAdharCardNo())
                .name(FullName.builder()
                        .firstName(employeeInput.getName().getFirstName())
                        .lastName(employeeInput.getName().getLastName())
                        .middleName(employeeInput.getName().getMiddleName())
                        .build())
                .dob(employeeInput.getDob())
                .mobileNo(employeeInput.getMobileNo())
                .email(employeeInput.getEmail())
                .gender(employeeInput.getGender())
                .bonus(employeeInput.getBonus())
                //.opId(patientInput.getOpId())
                .build();
        return this.employeeService.addEmployee(employee);

    }
    public Employee updateEmployee(String adharCardNo, long mobileNo){

        return this.employeeService.updateEmployee(adharCardNo,mobileNo);
    }
    public boolean deleteEmployee(String adharCardNo){

        return this.employeeService.deleteEmployee(adharCardNo);
    }

}
