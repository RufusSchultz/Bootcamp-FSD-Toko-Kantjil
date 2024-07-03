package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.CateringInputDto;
import com.backend.tokokantjil.dtos.outputs.CateringOutputDto;
import com.backend.tokokantjil.models.Catering;

public class CateringMapper {
    public static Catering fromCateringInputDtoToCatering(CateringInputDto cateringInputDto) {
        Catering c = new Catering();

        c.setDateAndTime(cateringInputDto.dateAndTime);
        c.setNumberOfPeople(cateringInputDto.numberOfPeople);
        c.setPrice(cateringInputDto.price);
        c.setNotes(cateringInputDto.notes);

        return c;
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

    public static Catering fromCateringToUpdatedCatering(Catering c, Catering cUpdate) {

        c.setDateAndTime(cUpdate.getDateAndTime());
        c.setNumberOfPeople(cUpdate.getNumberOfPeople());
        c.setPrice(cUpdate.getPrice());
        c.setNotes(cUpdate.getNotes());

        return c;
    }
}
