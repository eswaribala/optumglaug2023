package com.optum.patientapi.services;

import com.optum.patientapi.dtos.OptumBill;
import com.optum.patientapi.facades.BillingFacade;
import com.optum.patientapi.repositories.BillingRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.annotation.StreamRetryTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BillingService {

    @Autowired
    private BillingFacade billingFacade;
    @Autowired
    private BillingRepo billingRepo;

    @StreamListener(target = BillingFacade.inChannelName)
    public void getBills(@Payload OptumBill bill){

        log.info("Message Received"+bill.getBillNo()+","+bill.getBillAmount());
        this.billingRepo.save(bill);

    }
}
