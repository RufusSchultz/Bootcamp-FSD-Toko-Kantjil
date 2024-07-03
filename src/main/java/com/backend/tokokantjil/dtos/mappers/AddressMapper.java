package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.AddressInputDto;
import com.backend.tokokantjil.dtos.outputs.AddressOutputDto;
import com.backend.tokokantjil.models.Address;

public class AddressMapper {
    public static Address fromAddressInputDtoToAddress(AddressInputDto addressInputDto) {
        Address p = new Address();

        p.setName(addressInputDto.name);
        p.setStreet(addressInputDto.street);
        p.setHouseNumber(addressInputDto.houseNumber);
        p.setHouseNumberSuffix(addressInputDto.houseNumberSuffix);
        p.setPostalCode(addressInputDto.postalCode);
        p.setCity(addressInputDto.city);
        p.setNotes(addressInputDto.notes);

        return p;
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

        return addressOutputDto;
    }

    public static Address fromAddressToUpdatedAddress(Address a, Address aUpdate) {

        a.setName(aUpdate.getName());
        a.setStreet(aUpdate.getStreet());
        a.setHouseNumber(aUpdate.getHouseNumber());
        a.setHouseNumberSuffix(aUpdate.getHouseNumberSuffix());
        a.setPostalCode(aUpdate.getPostalCode());
        a.setCity(aUpdate.getCity());
        a.setNotes(aUpdate.getNotes());

        return a;
    }
}
