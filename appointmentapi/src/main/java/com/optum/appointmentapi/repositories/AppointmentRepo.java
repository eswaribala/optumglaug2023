package com.optum.appointmentapi.repositories;

import com.optum.appointmentapi.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment,Long> {
}
