package com.optum.datapublisher.services;

import com.optum.datapublisher.facades.BillingFacade;
import com.optum.datapublisher.models.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class BillingService {

    @Autowired
    private BillingFacade billingFacade;


    public boolean publishBill(Bill bill){
        MessageChannel messageChannel=billingFacade.outChannel();

        return messageChannel.send(MessageBuilder
                .withPayload(bill)
                .setHeader(MessageHeaders.CONTENT_TYPE,
                        MimeTypeUtils.APPLICATION_JSON)
                .build());


    }
}
