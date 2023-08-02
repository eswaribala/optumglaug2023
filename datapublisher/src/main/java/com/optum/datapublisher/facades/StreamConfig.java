package com.optum.datapublisher.facades;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(BillingFacade.class)
public class StreamConfig {
}
