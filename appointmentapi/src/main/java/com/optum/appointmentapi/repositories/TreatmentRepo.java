package com.optum.appointmentapi.repositories;

import com.optum.appointmentapi.models.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TreatmentRepo extends JpaRepository<Treatment,Long> {

    @Query("Select t from Treatment t where t.appointment.appointmentNo=:appointmentNo")
    public List<Treatment> findTreatmentsByAppointmentNo(@Param("appointmentNo") long appointmentNo);

}
