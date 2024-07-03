package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.inputs.AddressInputDto;
import com.backend.tokokantjil.dtos.mappers.AddressMapper;
import com.backend.tokokantjil.dtos.outputs.AddressOutputDto;
import com.backend.tokokantjil.exceptions.RecordNotFoundException;
import com.backend.tokokantjil.models.Address;
import com.backend.tokokantjil.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<AddressOutputDto> getAllAddresss() {
        List<AddressOutputDto> list = new ArrayList<>();
        for (Address i: this.addressRepository.findAll()) {
            list.add(AddressMapper.fromAddressToAddressOutputDto(i));
        }
        return list;
    }

    public AddressOutputDto getAddressById(Long id) {
        Address p = this.addressRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No address with id " + id + " found."));
        return AddressMapper.fromAddressToAddressOutputDto(p);
    }

    public AddressOutputDto createAddress(AddressInputDto addressInputDto) {
        Address p = this.addressRepository.save(AddressMapper.fromAddressInputDtoToAddress(addressInputDto));
        return AddressMapper.fromAddressToAddressOutputDto(p);
    }

    public void deleteAddress(Long id) {
        if(this.addressRepository.findById(id).isPresent()) {
            this.addressRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No address with id " + id + " found.");
        }
    }

    public AddressOutputDto updateAddress(Long id, AddressInputDto addressInputDto) {
        Address a1 = this.addressRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No address with id " + id + " found."));
        Address a2 = AddressMapper.fromAddressInputDtoToAddress(addressInputDto);
        Address a3 = this.addressRepository.save(AddressMapper.fromAddressToUpdatedAddress(a1, a2));

        return AddressMapper.fromAddressToAddressOutputDto(a3);
    }
}
