package com.optum.appointmentapi.dtos;

import com.optum.appointmentapi.models.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentInput {
    private long treatmentId;
    private String treatmentInfo;
    private LocalDateTime treatmentDate;
    private long cost;
    private int discount;
    private AppointmentInput appointment;
}
