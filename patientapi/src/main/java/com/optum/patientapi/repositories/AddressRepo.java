package com.optum.patientapi.repositories;

import com.optum.patientapi.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address,Long> {
}
