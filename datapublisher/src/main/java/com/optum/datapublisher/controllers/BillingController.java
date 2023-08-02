package com.optum.datapublisher.controllers;

import com.optum.datapublisher.dtos.ResponseWrapper;
import com.optum.datapublisher.models.Bill;
import com.optum.datapublisher.services.BillingService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BillingController {
    @Autowired
    private BillingService billingService;

     @PostMapping({"/v1.0"})
    public ResponseEntity<ResponseWrapper> publishBill(@RequestBody Bill bill){
         if(this.billingService.publishBill(bill)){
             return  ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Message Published..."));

         }
         else
             return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("Message Not Published ..."));

    }
}
