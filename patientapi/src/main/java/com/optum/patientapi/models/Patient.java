package com.optum.patientapi.models;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="Patient")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class Patient extends  Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="OPID")
    private long opId;




}
