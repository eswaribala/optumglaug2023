package com.optum.patientapi.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FullNameInput {


    private String firstName;

    private String middleName;

    private String lastName;
}
