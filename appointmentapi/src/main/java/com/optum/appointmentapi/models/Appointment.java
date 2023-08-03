package com.optum.appointmentapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Appointment")
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Appointment_No")
    private long appointmentNo;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name="Appointment_Date")
    private LocalDateTime appointmentDate;
    @Column(name="OPID")
    private long opId;
    @Column(name="Doctor_Name")
    private String doctorName;
    @Column(name="Notes")
    private String notes;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "appointment")
    private List<Treatment> treatments;
}
