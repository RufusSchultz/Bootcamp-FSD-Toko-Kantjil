package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.CateringInputDto;
import com.backend.tokokantjil.dtos.outputs.CateringOutputDto;
import com.backend.tokokantjil.dtos.outputs.DishOutputDto;
import com.backend.tokokantjil.dtos.outputs.ProductOutputDto;
import com.backend.tokokantjil.models.Catering;

import java.util.ArrayList;
import java.util.List;

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

        if (catering.getProducts() != null) {
            List<ProductOutputDto> productOutputDtoList = new ArrayList<>();
            for (int i = catering.getProducts().size(); i < catering.getProducts().size(); i++) {
                productOutputDtoList.add(ProductMapper.fromProductToProductOutputDto(catering.getProducts().get(i)));
            }
            cateringOutputDto.setProductOutputDtoList(productOutputDtoList);
        }

        if (catering.getDishes() != null) {
            List<DishOutputDto> dishOutputDtoList = new ArrayList<>();
            for (int i = catering.getDishes().size(); i < catering.getDishes().size(); i++) {
                dishOutputDtoList.add(DishMapper.fromDishToDishOutputDto(catering.getDishes().get(i)));
            }
            cateringOutputDto.setDishOutputDtoList(dishOutputDtoList);
        }


        return cateringOutputDto;
    }

    public static Catering fromCateringToUpdatedCatering(Catering catering, Catering cateringUpdate) {

        catering.setDateAndTime(cateringUpdate.getDateAndTime());
        catering.setNumberOfPeople(cateringUpdate.getNumberOfPeople());
        catering.setPrice(cateringUpdate.getPrice());
        catering.setNotes(cateringUpdate.getNotes());
        catering.setProducts(cateringUpdate.getProducts());

        return catering;
    }
}
