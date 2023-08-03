package com.optum.appointmentapi.dataloaders;

import com.netflix.graphql.dgs.DgsDataLoader;
import com.optum.appointmentapi.models.Appointment;
import com.optum.appointmentapi.repositories.AppointmentRepo;
import org.dataloader.BatchLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@DgsDataLoader(name="appointments")
public class AppointmentDataLoader implements BatchLoader<Long, Appointment> {
    @Autowired
    private AppointmentRepo appointmentRepo;


    @Override
    public CompletionStage<List<Appointment>> load(List<Long> keys) {
        return CompletableFuture.supplyAsync(()->this.appointmentRepo.findAllById(keys)) ;
    }
}
