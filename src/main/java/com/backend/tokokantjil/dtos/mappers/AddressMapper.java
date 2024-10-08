package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.AddressInputDto;
import com.backend.tokokantjil.dtos.outputs.AddressOutputDto;
import com.backend.tokokantjil.models.Address;

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
