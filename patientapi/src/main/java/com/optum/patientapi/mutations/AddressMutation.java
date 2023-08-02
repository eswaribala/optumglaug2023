package com.optum.patientapi.mutations;

import com.optum.patientapi.dtos.AddressInput;
import com.optum.patientapi.models.Address;
import com.optum.patientapi.models.FullName;
import com.optum.patientapi.models.Patient;
import com.optum.patientapi.models.Person;
import com.optum.patientapi.services.AddressService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AddressMutation implements GraphQLMutationResolver {

    @Autowired
    private AddressService addressService;

    public Address addAddress(String adharCardNo, AddressInput addressInput){
        Address address=Address.builder()
                .addressId(0)
                .doorNo(addressInput.getDoorNo())
                .streetName(addressInput.getStreetName())
                .city(addressInput.getCity())
                .pincode(addressInput.getPincode())

                .build();
         return this.addressService.addAddress(adharCardNo,address);


    }
    public Address updateAddress(AddressInput addressInput){

        Address address=Address.builder()
                .addressId(addressInput.getAddressId())
                .doorNo(addressInput.getDoorNo())
                .streetName(addressInput.getStreetName())
                .city(addressInput.getCity())
                .pincode(addressInput.getPincode())
                .person(Person.builder()
                        .adharCardNo(addressInput.getPerson().getAdharCardNo())
                        .name(FullName.builder()
                                .firstName(addressInput.getPerson().getName().getFirstName())
                                .lastName(addressInput.getPerson().getName().getLastName())
                                .middleName(addressInput.getPerson().getName().getMiddleName())
                                .build())
                        .dob(addressInput.getPerson().getDob())
                        .mobileNo(addressInput.getPerson().getMobileNo())
                        .email(addressInput.getPerson().getEmail())
                        .gender(addressInput.getPerson().getGender())
                       // .opId(addressInput.getPatient().getOpId())



                        .build())
                .build();
        return this.addressService.updateAddress(address);

    }
    public boolean deleteAddress(long addressId){
        return this.addressService.deleteAddress(addressId);
    }
}
