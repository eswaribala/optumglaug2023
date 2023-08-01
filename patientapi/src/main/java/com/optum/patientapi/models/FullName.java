package com.optum.patientapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullName {
    @Column(name="First_Name",nullable = false,length = 100)
    private String firstName;
    @Column(name="Middle_Name",nullable = true,length = 100)
    private String middleName;
    @Column(name="Last_Name",nullable = false,length = 100)
    private String lastName;
}
