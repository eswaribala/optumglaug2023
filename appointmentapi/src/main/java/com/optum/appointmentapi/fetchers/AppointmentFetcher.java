package com.optum.appointmentapi.fetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;
import com.optum.appointmentapi.models.Appointment;
import com.optum.appointmentapi.models.Treatment;
import com.optum.appointmentapi.repositories.AppointmentRepo;
import com.optum.appointmentapi.repositories.TreatmentRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@DgsComponent
public class AppointmentFetcher {

    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private TreatmentRepo treatmentRepo;

    @DgsQuery
    public List<Appointment> findAllAppointments(){
        return this.appointmentRepo.findAll();
    }

    @DgsQuery
    public Appointment findAppointmentById(long appointmentNo){
        return this.appointmentRepo.findById(appointmentNo).orElse(null);
    }

    @DgsData(parentType = "Appointment", field = "treatments")
    public List<Treatment> treatments(DgsDataFetchingEnvironment dgsDataFetchingEnvironment) {
        Appointment appointment = dgsDataFetchingEnvironment.getSource();
        List<Treatment> treatmentsList = new ArrayList<>();
        for (Treatment treatment : appointment.getTreatments()) {
            Treatment treatmentResponse = treatmentRepo.findById(treatment.getTreatmentId()).get();
            treatmentsList.add(treatmentResponse);
        }
        return treatmentsList;
    }
}
