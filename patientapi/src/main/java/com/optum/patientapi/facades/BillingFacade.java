package com.optum.patientapi.facades;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface BillingFacade {
    String inChannelName="in-channel";

    @Input(inChannelName)
    MessageChannel inChannel();

}
