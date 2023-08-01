package com.optum.patientapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name="Person")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public class Person {

    @Id
    @Column(name="Adhar_Card")
    private String adharCard;

    @Embedded
    private FullName name;

    @Enumerated(EnumType.STRING)
    @Column(name="Gender")
    private Gender gender;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="DOB")
    private LocalDate dob;
    @Column(name="Email",nullable = false,length = 200)
    private String email;
    @Column(name="Mobile_No")
    private long mobileNo;
}
