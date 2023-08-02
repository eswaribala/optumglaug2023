package com.optum.patientapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class AddressInput {


    private long addressId;

    private String doorNo;

    private String streetName;

    private String city;

    private long pincode;

    private PersonInput person;
}
