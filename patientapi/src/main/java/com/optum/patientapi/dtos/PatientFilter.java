package com.optum.patientapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientFilter {

    private FilterField opId;
    private FilterField email;
    private GenderFilterField gender;
    private List<PatientFilter> and;
    private List<PatientFilter> or;


}
