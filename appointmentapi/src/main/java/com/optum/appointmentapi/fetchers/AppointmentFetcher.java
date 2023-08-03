package com.optum.appointmentapi.fetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.optum.appointmentapi.models.Appointment;
import com.optum.appointmentapi.repositories.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class AppointmentFetcher {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @DgsQuery
    public List<Appointment> findAllAppointments(){
        return this.appointmentRepo.findAll();
    }

    @DgsQuery
    public Appointment findAppointmentById(long appointmentNo){
        return this.appointmentRepo.findById(appointmentNo).orElse(null);
    }
}
