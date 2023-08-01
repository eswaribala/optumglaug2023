package com.optum.patientapi.services;

import com.optum.patientapi.models.Address;
import com.optum.patientapi.models.Patient;
import com.optum.patientapi.repositories.AddressRepo;
import com.optum.patientapi.repositories.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepo addressRepo;

    private PatientRepo patientRepo;


    public Address addPatient(String adharCardNo, Address address){

        Patient patient=this.patientRepo.findById(adharCardNo).orElse(null);
        if(patient!=null){
            address.setPatient(patient);
            return this.addressRepo.save(address);

        }

        return null;
    }

    //getall

    public List<Address> getAllAddresses(){
        return this.addressRepo.findAll();
    }

    public List<Address> getAddressByAdharCardNo(String adharCardNo){
        return this.addressRepo.findAddressByAdharCardNo(adharCardNo);
    }



    //update

    public Address updateAddress(Address address){

        return this.addressRepo.save(address);


    }

    //delete

    public boolean deleteAddress(long addressId){
        boolean status=false;
        Address address=this.addressRepo.findById(addressId).orElse(null);
        if(address!=null){
            this.addressRepo.deleteById(addressId);
            status=true;

        }
        return status;

    }
}
