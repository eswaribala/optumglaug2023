package com.optum.appointmentapi.fetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.optum.appointmentapi.models.Treatment;
import com.optum.appointmentapi.repositories.TreatmentRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class TreatmentFetcher {

    @Autowired
    private TreatmentRepo treatmentRepo;

    @DgsQuery
    public List<Treatment> findAllTreatments(){
           return this.treatmentRepo.findAll();
    }
    @DgsQuery
    public List<Treatment> findTreatmentById(long appointmentNo){
        return this.treatmentRepo.findTreatmentsByAppointmentNo(appointmentNo);
    }

}
