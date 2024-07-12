package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.CateringInputDto;
import com.backend.tokokantjil.dtos.outputs.CateringOutputDto;
import com.backend.tokokantjil.models.Catering;

public class CateringMapper {
    public static Catering fromCateringInputDtoToCatering(CateringInputDto cateringInputDto) {
        Catering catering = new Catering();

        catering.setDateAndTime(cateringInputDto.dateAndTime);
        catering.setNumberOfPeople(cateringInputDto.numberOfPeople);
        catering.setPrice(cateringInputDto.price);
        catering.setNotes(cateringInputDto.notes);

        return catering;
    }

    public static CateringOutputDto fromCateringToCateringOutputDto(Catering catering) {
        CateringOutputDto cateringOutputDto = new CateringOutputDto();

        cateringOutputDto.setId(catering.getId());
        cateringOutputDto.setDateAndTime(catering.getDateAndTime());
        cateringOutputDto.setNumberOfPeople(catering.getNumberOfPeople());
        cateringOutputDto.setPrice(catering.getPrice());
        cateringOutputDto.setNotes(catering.getNotes());

        return cateringOutputDto;
    }

    public static Catering fromCateringToUpdatedCatering(Catering catering, Catering cateringUpdate) {

        catering.setDateAndTime(cateringUpdate.getDateAndTime());
        catering.setNumberOfPeople(cateringUpdate.getNumberOfPeople());
        catering.setPrice(cateringUpdate.getPrice());
        catering.setNotes(cateringUpdate.getNotes());

        return catering;
    }
}
