package com.example.showdgs;

import com.example.showdgs.instrumentation.FederatedTracingInstrumentation;
import graphql.execution.instrumentation.Instrumentation;
import net.datafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShowDgsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShowDgsApplication.class, args);
    }
    @Bean
    @ConditionalOnProperty( prefix = "graphql.tracing", name = "enabled", matchIfMissing = true)
    public Instrumentation tracingInstrumentation(){
        return new FederatedTracingInstrumentation();
    }


    @Bean
    public Faker getFaker(){
        return new Faker();
    }

}
