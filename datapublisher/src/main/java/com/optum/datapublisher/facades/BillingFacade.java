package com.optum.datapublisher.facades;

import org.intellij.lang.annotations.JdkConstants;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface BillingFacade {

    String outChannelName="out-channel";

    @Output(outChannelName)
    MessageChannel outChannel();
}
