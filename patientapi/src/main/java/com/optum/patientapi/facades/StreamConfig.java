package com.optum.patientapi.facades;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(BillingFacade.class)
public class StreamConfig {
}
