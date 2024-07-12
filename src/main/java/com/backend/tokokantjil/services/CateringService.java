package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.inputs.CateringInputDto;
import com.backend.tokokantjil.dtos.mappers.CateringMapper;
import com.backend.tokokantjil.dtos.outputs.CateringOutputDto;
import com.backend.tokokantjil.exceptions.RecordNotFoundException;
import com.backend.tokokantjil.models.Catering;
import com.backend.tokokantjil.repositories.CateringRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CateringService {
    private final CateringRepository cateringRepository;

    public CateringService(CateringRepository cateringRepository) {
        this.cateringRepository = cateringRepository;
    }

    public List<CateringOutputDto> getAllCaterings() {
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

    public CateringOutputDto createCatering(CateringInputDto cateringInputDto) {
        Catering catering = this.cateringRepository.save(CateringMapper.fromCateringInputDtoToCatering(cateringInputDto));
        return CateringMapper.fromCateringToCateringOutputDto(catering);
    }

    public void deleteCatering(Long id) {
        if(this.cateringRepository.findById(id).isPresent()) {
            this.cateringRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No catering with id " + id + " found.");
        }
    }

    public CateringOutputDto updateCatering(Long id, CateringInputDto cateringInputDto) {
        Catering oldCatering = this.cateringRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No catering with id " + id + " found."));
        Catering cateringUpdate = CateringMapper.fromCateringInputDtoToCatering(cateringInputDto);
        Catering newCatering = this.cateringRepository.save(CateringMapper.fromCateringToUpdatedCatering(oldCatering, cateringUpdate));

        return CateringMapper.fromCateringToCateringOutputDto(newCatering);
    }
}
