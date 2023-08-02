package com.optum.patientapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientFilter {

    private FilterField opId;
    private FilterField email;
    private GenderFilterField gender;
}
