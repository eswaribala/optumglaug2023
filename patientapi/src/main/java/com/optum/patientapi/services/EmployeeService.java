package com.optum.patientapi.services;


import com.optum.patientapi.models.Employee;
import com.optum.patientapi.models.Patient;
import com.optum.patientapi.repositories.EmployeeRepo;
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
public class EmployeeService {


    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EntityManager entityManager;

    //insert

    public Employee addEmployee(Employee Employee){

        return this.employeeRepo.save(Employee);
    }

    //getall

    public List<Employee> getAllEmployees(){
        return this.employeeRepo.findAll();
    }

    public Employee getEmployeeById(String adharCardNo){
        return this.employeeRepo.findById(adharCardNo).orElse(null);
    }



    public Employee updateEmployee(String adharCardNo, long mobileNo){

        Employee employee=this.getEmployeeById(adharCardNo);
        if(employee!=null){
            employee.setMobileNo(mobileNo);
            this.employeeRepo.save(employee);
            return employee;
        }
        else
            return null;


    }

    //delete

    public boolean deleteEmployee(String adharCardNo){
        boolean status=false;
        Employee employee=this.getEmployeeById(adharCardNo);
        if(employee!=null){
            this.employeeRepo.deleteById(adharCardNo);
            status=true;

        }
        return status;

    }

}
