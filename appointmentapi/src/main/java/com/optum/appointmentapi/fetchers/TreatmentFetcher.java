package com.optum.appointmentapi.fetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import com.optum.appointmentapi.models.Appointment;
import com.optum.appointmentapi.models.Treatment;
import com.optum.appointmentapi.repositories.TreatmentRepo;
import graphql.schema.DataFetchingEnvironment;
import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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

    @DgsData(parentType = "Treatment",field = "appointment")
    public CompletableFuture<Appointment> appointments(DataFetchingEnvironment dfe){
        DataLoader<Long,Appointment> dataLoader = dfe.getDataLoader("appointments");
        Treatment treatment = dfe.getSource();
        return dataLoader.load(treatment.getAppointment().getAppointmentNo());

    }

}
