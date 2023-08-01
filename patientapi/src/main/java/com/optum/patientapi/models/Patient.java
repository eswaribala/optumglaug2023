package com.optum.patientapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="Patient")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="OPID")
    private long opId;

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
