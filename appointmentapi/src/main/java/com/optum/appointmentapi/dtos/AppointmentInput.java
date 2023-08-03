package com.optum.appointmentapi.dtos;

import com.optum.appointmentapi.models.Treatment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AppointmentInput {

    private long appointmentNo;
    private LocalDate appointmentDate;
    private long opId;
    private String doctorName;
    private String notes;
    private List<TreatmentInput> treatments;
}
