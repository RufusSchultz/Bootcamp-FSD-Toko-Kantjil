package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.inputs.CateringInputDto;
import com.backend.tokokantjil.dtos.mappers.CateringMapper;
import com.backend.tokokantjil.dtos.outputs.CateringOutputDto;
import com.backend.tokokantjil.exceptions.RecordNotFoundException;
import com.backend.tokokantjil.models.Address;
import com.backend.tokokantjil.models.Catering;
import com.backend.tokokantjil.repositories.AddressRepository;
import com.backend.tokokantjil.repositories.CateringRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CateringService {
    private final CateringRepository cateringRepository;
    private final AddressRepository addressRepository;

    public CateringService(CateringRepository cateringRepository, AddressRepository addressRepository) {
        this.cateringRepository = cateringRepository;
        this.addressRepository = addressRepository;
    }

    public List<CateringOutputDto> getEveryCatering() {
        List<CateringOutputDto> list = new ArrayList<>();
        for (Catering i: this.cateringRepository.findAll()) {
            list.add(CateringMapper.fromCateringToCateringOutputDto(i));
        }
        return list;
    }

    public CateringOutputDto getCateringById(Long id) {
        Catering catering = this.cateringRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No catering with id " + id + " found."));
        return CateringMapper.fromCateringToCateringOutputDto(catering);
    }

    public CateringOutputDto createNewCatering(CateringInputDto cateringInputDto) {
        Catering catering = this.cateringRepository.save(CateringMapper.fromCateringInputDtoToCatering(cateringInputDto));
        return CateringMapper.fromCateringToCateringOutputDto(catering);
    }

    public void deleteCateringById(Long id) {
        if(this.cateringRepository.findById(id).isPresent()) {
            this.cateringRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No catering with id " + id + " found.");
        }
    }

    public CateringOutputDto updateCateringWithNewCateringInputDto(Long id, CateringInputDto cateringInputDto) {
        Catering oldCatering = this.cateringRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No catering with id " + id + " found."));
        Catering cateringUpdate = CateringMapper.fromCateringInputDtoToCatering(cateringInputDto);
        Catering newCatering = this.cateringRepository.save(CateringMapper.fromCateringToUpdatedCatering(oldCatering, cateringUpdate));

        return CateringMapper.fromCateringToCateringOutputDto(newCatering);
    }

    public CateringOutputDto setCateringAddress(Long id, Long addressId) {
        Catering catering = this.cateringRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No catering with id " + id + " found."));
        Address address = this.addressRepository.findById(addressId).orElseThrow(() -> new RecordNotFoundException("No address with id " + id + " found."));

        catering.setAddress(address);
        this.cateringRepository.save(catering);

        return CateringMapper.fromCateringToCateringOutputDto(catering);
    }
}
