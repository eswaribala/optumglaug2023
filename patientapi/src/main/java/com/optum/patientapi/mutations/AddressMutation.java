package com.optum.patientapi.mutations;

import com.optum.patientapi.dtos.AddressInput;
import com.optum.patientapi.models.Address;
import com.optum.patientapi.models.FullName;
import com.optum.patientapi.models.Patient;
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
                .patient(Patient.builder()
                        .adharCardNo(addressInput.getPatient().getAdharCardNo())
                        .name(FullName.builder()
                                .firstName(addressInput.getPatient().getName().getFirstName())
                                .lastName(addressInput.getPatient().getName().getLastName())
                                .middleName(addressInput.getPatient().getName().getMiddleName())
                                .build())
                        .dob(addressInput.getPatient().getDob())
                        .mobileNo(addressInput.getPatient().getMobileNo())
                        .email(addressInput.getPatient().getEmail())
                        .gender(addressInput.getPatient().getGender())
                        .opId(addressInput.getPatient().getOpId())



                        .build())
                .build();
        return this.addressService.updateAddress(address);

    }
    public boolean deleteAddress(long addressId){
        return this.addressService.deleteAddress(addressId);
    }
}
