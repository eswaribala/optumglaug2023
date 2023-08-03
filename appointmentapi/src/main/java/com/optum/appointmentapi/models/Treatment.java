package com.optum.appointmentapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Treatment")
@Builder
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Treatment_Id")
    private long treatmentId;
    @Column(name="Treatment_Info")
    private String treatmentInfo;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="Treatment_Date")
    private LocalDate treatmentDate;
    @Column(name="Cost")
    private long cost;
    @Column(name="Discount")
    private int discount;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "Appointment_No"), name = "Appointment_No")
    private Appointment appointment;

}
