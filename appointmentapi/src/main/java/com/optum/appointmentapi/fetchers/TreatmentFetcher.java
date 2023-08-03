package com.optum.appointmentapi.fetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.optum.appointmentapi.dtos.TreatmentInput;
import com.optum.appointmentapi.models.Appointment;
import com.optum.appointmentapi.models.Treatment;
import com.optum.appointmentapi.repositories.AppointmentRepo;
import com.optum.appointmentapi.repositories.TreatmentRepo;
import graphql.schema.DataFetchingEnvironment;
import net.datafaker.providers.base.App;
import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@DgsComponent
public class TreatmentFetcher {

    @Autowired
    private TreatmentRepo treatmentRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

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

    @DgsMutation
    public Treatment addTreatment(TreatmentInput treatmentInput){

        Appointment appointment=this.appointmentRepo.findById(treatmentInput.getAppointment().getAppointmentNo()).orElse(null);

       if(appointment!=null) {
           Treatment treatment = Treatment.builder()
                   .treatmentId(treatmentInput.getTreatmentId())
                   .treatmentInfo(treatmentInput.getTreatmentInfo())
                   .treatmentDate(treatmentInput.getTreatmentDate())
                   .cost(treatmentInput.getCost())
                   .discount(treatmentInput.getDiscount())
                   .appointment(appointment)
                   .build();
           return treatmentRepo.save(treatment);
       }
       else
           return null;
    }
    @DgsMutation
    public Treatment updateTreatment(long treatmentId, int discount){

        Treatment treatment=this.treatmentRepo.findById(treatmentId).orElse(null);
        if(treatment!=null){
            treatment.setDiscount(discount);
            return this.treatmentRepo.save(treatment);
        }
        else
            return null;
    }

    @DgsMutation
    public boolean deleteTreatment(long treatmentId){

        boolean status=false;
        this.treatmentRepo.deleteById(treatmentId);
        Treatment treatment=this.treatmentRepo.findById(treatmentId).orElse(null);
        if(treatment==null)
            status=true;

        return status;
    }
}
