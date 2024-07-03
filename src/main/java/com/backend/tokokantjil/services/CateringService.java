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
        Catering p = this.cateringRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No catering with id " + id + " found."));
        return CateringMapper.fromCateringToCateringOutputDto(p);
    }

    public CateringOutputDto createCatering(CateringInputDto cateringInputDto) {
        Catering p = this.cateringRepository.save(CateringMapper.fromCateringInputDtoToCatering(cateringInputDto));
        return CateringMapper.fromCateringToCateringOutputDto(p);
    }

    public void deleteCatering(Long id) {
        if(this.cateringRepository.findById(id).isPresent()) {
            this.cateringRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No catering with id " + id + " found.");
        }
    }

    public CateringOutputDto updateCatering(Long id, CateringInputDto cateringInputDto) {
        Catering c1 = this.cateringRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No catering with id " + id + " found."));
        Catering c2 = CateringMapper.fromCateringInputDtoToCatering(cateringInputDto);
        Catering c3 = this.cateringRepository.save(CateringMapper.fromCateringToUpdatedCatering(c1, c2));

        return CateringMapper.fromCateringToCateringOutputDto(c3);
    }
}
