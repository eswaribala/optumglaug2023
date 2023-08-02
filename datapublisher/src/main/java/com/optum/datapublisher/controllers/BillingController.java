package com.optum.datapublisher.controllers;

import com.optum.datapublisher.dtos.ResponseWrapper;
import com.optum.datapublisher.models.OptumBill;
import com.optum.datapublisher.services.BillingService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
@Slf4j
public class BillingController {
    @Autowired
    private BillingService billingService;

     @PostMapping({"/v1.0"})
    public ResponseEntity<ResponseWrapper> publishBill(@RequestBody OptumBill bill){
         log.info("Data"+bill.getBillNo());

         if(this.billingService.publishBill(bill)){
             return  ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Message Published..."));

         }
         else
             return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("Message Not Published ..."));

    }
}
