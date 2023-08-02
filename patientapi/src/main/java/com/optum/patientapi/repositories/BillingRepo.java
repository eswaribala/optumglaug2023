package com.optum.patientapi.repositories;

import com.optum.patientapi.dtos.OptumBill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BillingRepo extends MongoRepository<OptumBill,Long> {
}
