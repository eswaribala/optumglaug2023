package com.optum.patientapi.subscriptions;

import com.optum.patientapi.dtos.OptumBill;
import com.optum.patientapi.services.BillingService;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@Component
public class BillSubscription implements GraphQLSubscriptionResolver {

    @Autowired
     private BillingService billingService;

    public Publisher<List<OptumBill>> billSubscription(){
        if(billingService.getAllBills().size()>0)
            return Flux.interval(Duration.ofSeconds(5))

                    .map(num -> billingService.getAllBills());
        else
            return null;

    }
}
