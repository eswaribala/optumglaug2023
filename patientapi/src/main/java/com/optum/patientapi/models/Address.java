package com.optum.patientapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="Address")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Addres_Id")
    private long addressId;
    @Column(name="Door_No",nullable = false,length = 5)
    private String doorNo;
    @Column(name="Strret_Name",nullable = false,length = 150)
    private String streetName;
    @Column(name="City",nullable = false,length = 150)
    private String city;
    @Column(name="Pincode")
    private long pincode;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(foreignKey = @ForeignKey(name = "Adhar_Card_No"), name = "Adhar_Card_No")
    private Person person;

}
