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
        catering.setTotalCostPrice(cateringInputDto.totalCostPrice);
        catering.setTotalSellPrice(cateringInputDto.totalSellPrice);
        catering.setAgreedPrice(cateringInputDto.agreedPrice);
        catering.setAppraised(false);
        catering.setNotes(cateringInputDto.notes);

        return catering;
    }

    public static CateringOutputDto fromCateringToCateringOutputDto(Catering catering) {
        CateringOutputDto cateringOutputDto = new CateringOutputDto();

        cateringOutputDto.setId(catering.getId());
        cateringOutputDto.setDateAndTime(catering.getDateAndTime());
        cateringOutputDto.setNumberOfPeople(catering.getNumberOfPeople());
        cateringOutputDto.setTotalCostPrice(catering.getTotalCostPrice());
        cateringOutputDto.setTotalSellPrice(catering.getTotalSellPrice());
        cateringOutputDto.setAgreedPrice(catering.getAgreedPrice());
        cateringOutputDto.setAppraised(catering.isAppraised());
        cateringOutputDto.setNotes(catering.getNotes());

        if (catering.getProducts() != null) {
            List<ProductOutputDto> productOutputDtoList = new ArrayList<>();
            for (int i = catering.getProducts().size(); i < catering.getProducts().size(); i++) {
                productOutputDtoList.add(ProductMapper.fromProductToProductOutputDto(catering.getProducts().get(i)));
            }
            cateringOutputDto.setProducts(productOutputDtoList);
        }
        if (catering.getDishes() != null) {
            List<DishOutputDto> dishOutputDtoList = new ArrayList<>();
            for (int i = catering.getDishes().size(); i < catering.getDishes().size(); i++) {
                dishOutputDtoList.add(DishMapper.fromDishToDishOutputDto(catering.getDishes().get(i)));
            }
            cateringOutputDto.setDishes(dishOutputDtoList);
        }
        if (catering.getAddress() != null) {
            cateringOutputDto.setAddress(AddressMapper.fromAddressToAddressOutputDto(catering.getAddress()));
        }


        return cateringOutputDto;
    }

    public static Catering fromCateringToUpdatedCatering(Catering catering, Catering cateringUpdate) {

        catering.setDateAndTime(cateringUpdate.getDateAndTime());
        catering.setNumberOfPeople(cateringUpdate.getNumberOfPeople());
        catering.setTotalCostPrice(catering.getTotalCostPrice());
        catering.setTotalSellPrice(catering.getTotalSellPrice());
        catering.setAgreedPrice(cateringUpdate.getAgreedPrice());
        catering.setAppraised(cateringUpdate.isAppraised());
        catering.setNotes(cateringUpdate.getNotes());
        catering.setProducts(cateringUpdate.getProducts());

        return catering;
    }
}
