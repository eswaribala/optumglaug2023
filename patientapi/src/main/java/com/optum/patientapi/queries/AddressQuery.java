package com.optum.patientapi.queries;

import com.optum.patientapi.models.Address;
import com.optum.patientapi.services.AddressService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressQuery implements GraphQLQueryResolver {

    @Autowired
    private AddressService addressService;

    public List<Address> findAllAddresses(){
        return this.addressService.getAllAddresses();
    }

    public List<Address> findAddressByAdharCardNo(String adharCardNo){
        return this.addressService.getAddressByAdharCardNo(adharCardNo);
    }

}
