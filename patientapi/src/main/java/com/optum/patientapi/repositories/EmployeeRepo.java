package com.optum.patientapi.repositories;

import com.optum.patientapi.models.Employee;
import com.optum.patientapi.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee,String> {



}
