package com.optum.patientapi.repositories;

import com.optum.patientapi.models.Address;
import com.optum.patientapi.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepo extends JpaRepository<Address,Long> {

    @Query("Select a from Address a where a.patient.adharCardNo=:adharCardNo")
    public List<Address> findAddressByAdharCardNo(@Param("adharCardNo") String adharCardNo);
}
