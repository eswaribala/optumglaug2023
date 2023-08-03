package com.optum.appointmentapi.fetchers;

import com.netflix.graphql.dgs.*;
import com.optum.appointmentapi.dtos.AppointmentInput;
import com.optum.appointmentapi.dtos.TreatmentInput;
import com.optum.appointmentapi.exceptions.NoDataFoundError;
import com.optum.appointmentapi.models.Appointment;
import com.optum.appointmentapi.models.Treatment;
import com.optum.appointmentapi.repositories.AppointmentRepo;
import com.optum.appointmentapi.repositories.TreatmentRepo;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
        Appointment appointment=this.appointmentRepo.findById(appointmentNo).orElse(null);

        if(appointment==null){
            throw new NoDataFoundError("Appointment Not Found","10001");
        }
        else

        return appointment;
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


    @DgsMutation
    public Appointment addAppointment(@InputArgument AppointmentInput appointmentInput){

        Appointment appointment=Appointment.builder()
                .appointmentNo(appointmentInput.getAppointmentNo())
                .appointmentDate(appointmentInput.getAppointmentDate())
                .doctorName(appointmentInput.getDoctorName())
                .notes(appointmentInput.getNotes())
                .opId(appointmentInput.getOpId())
                .build();
        Appointment appointmentResponse= this.appointmentRepo.save(appointment);

        if(appointmentInput.getTreatments().size()>0){

            for(TreatmentInput treatment : appointmentInput.getTreatments()){
                Treatment treatmentObj=Treatment.builder()
                        .treatmentId(treatment.getTreatmentId())
                        .treatmentInfo(treatment.getTreatmentInfo())
                        .treatmentDate(treatment.getTreatmentDate())
                        .cost(treatment.getCost())
                        .discount(treatment.getDiscount())
                        .appointment(appointment)
                        .build();
                this.treatmentRepo.save(treatmentObj);

            }


        }

        appointmentResponse.setTreatments(mapAppointmentTreatments(appointmentInput.getTreatments()));
        return appointmentResponse;
    }

    private List<Treatment> mapAppointmentTreatments(List<TreatmentInput> treatmentInput) {
        List<Treatment> treatmentsList = treatmentInput.stream().map(t -> {
            Treatment treatment = Treatment.builder()
                    .treatmentId(t.getTreatmentId())
                    .treatmentInfo(t.getTreatmentInfo())
                    .treatmentDate(t.getTreatmentDate())
                    .cost(t.getCost())
                    .discount(t.getDiscount())
                    .build();
            return treatment;
        }).collect(Collectors.toList());
        return treatmentsList;
    }

    @DgsMutation
    public Appointment updateAppointment(@InputArgument long appointmentNo, @InputArgument LocalDate appointmentDate){

        Appointment appointment=this.appointmentRepo.findById(appointmentNo).orElse(null);
        if(appointment!=null){
            appointment.setAppointmentDate(appointmentDate);
            return this.appointmentRepo.save(appointment);
        }
        else
            return null;
    }

    @DgsMutation
    public boolean deleteAppointment(@InputArgument long appointmentNo){
          boolean status=false;
           this.appointmentRepo.deleteById(appointmentNo);
           Appointment appointment=this.appointmentRepo.findById(appointmentNo).orElse(null);
           if(appointment==null)
               status=true;

           return status;
    }



    @DgsSubscription
    public Publisher<Object> showSubscriptions(){

       Random random=new Random();

        return Flux.interval(Duration.ofSeconds(5))
                .map(obj -> appointmentRepo.findById(random.nextLong()));



    }

}
