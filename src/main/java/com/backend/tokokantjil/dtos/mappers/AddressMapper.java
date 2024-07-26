package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.AddressInputDto;
import com.backend.tokokantjil.dtos.outputs.AddressOutputDto;
import com.backend.tokokantjil.dtos.outputs.CateringOutputDto;
import com.backend.tokokantjil.dtos.outputs.CustomerOutputDto;
import com.backend.tokokantjil.models.Address;
import com.backend.tokokantjil.models.Catering;
import com.backend.tokokantjil.models.Customer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddressMapper {
    public static Address fromAddressInputDtoToAddress(AddressInputDto addressInputDto) {
        Address address = new Address();

        address.setName(addressInputDto.name);
        address.setStreet(addressInputDto.street);
        address.setHouseNumber(addressInputDto.houseNumber);
        address.setHouseNumberSuffix(addressInputDto.houseNumberSuffix);
        address.setPostalCode(addressInputDto.postalCode);
        address.setCity(addressInputDto.city);
        address.setNotes(addressInputDto.notes);

        return address;
    }

    public static AddressOutputDto fromAddressToAddressOutputDto(Address address) {
        AddressOutputDto addressOutputDto = new AddressOutputDto();

        addressOutputDto.setId(address.getId());
        addressOutputDto.setName(address.getName());
        addressOutputDto.setStreet(address.getStreet());
        addressOutputDto.setHouseNumber(address.getHouseNumber());
        addressOutputDto.setHouseNumberSuffix(address.getHouseNumberSuffix());
        addressOutputDto.setPostalCode(address.getPostalCode());
        addressOutputDto.setCity(address.getCity());
        addressOutputDto.setNotes(address.getNotes());

//        if (address.getCaterings() != null) {
//            Set<CateringOutputDto> cateringOutputDtoSet = new HashSet<>();
//            for (Catering catering : address.getCaterings()) {
//                cateringOutputDtoSet.add(CateringMapper.fromCateringToCateringOutputDto(catering));
//            }
//            addressOutputDto.setCaterings(cateringOutputDtoSet);
//        }
//        if (address.getCustomers() != null) {
//            Set<CustomerOutputDto> customerOutputDtoSet = new HashSet<>();
//            for (Customer customer : address.getCustomers()) {
//                customerOutputDtoSet.add(CustomerMapper.fromCustomerToCustomerOutputDto(customer));
//            }
//            addressOutputDto.setCustomers(customerOutputDtoSet);
//        }
        return addressOutputDto;
    }

    public static Address fromAddressToUpdatedAddress(Address address, Address addressUpdate) {

        address.setName(addressUpdate.getName());
        address.setStreet(addressUpdate.getStreet());
        address.setHouseNumber(addressUpdate.getHouseNumber());
        address.setHouseNumberSuffix(addressUpdate.getHouseNumberSuffix());
        address.setPostalCode(addressUpdate.getPostalCode());
        address.setCity(addressUpdate.getCity());
        address.setNotes(addressUpdate.getNotes());

        return address;
    }
}
