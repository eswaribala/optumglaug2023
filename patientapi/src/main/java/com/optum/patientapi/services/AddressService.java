package com.optum.patientapi.services;

import com.optum.patientapi.models.Address;
import com.optum.patientapi.models.Patient;
import com.optum.patientapi.models.Person;
import com.optum.patientapi.repositories.AddressRepo;
import com.optum.patientapi.repositories.PatientRepo;
import com.optum.patientapi.repositories.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private PersonRepo personRepo;


    public Address addAddress(String adharCardNo, Address address){

        Person person=this.personRepo.findById(adharCardNo).orElse(null);
        if(person!=null){
            address.setPerson(person);
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
