package com.optum.patientapi.dtos;

import com.optum.patientapi.models.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class EmployeeInput {



    private String adharCardNo;


    private FullNameInput name;


    private Gender gender;

    private LocalDate dob;

    private String email;

    private long mobileNo;
    private long bonus;
}
