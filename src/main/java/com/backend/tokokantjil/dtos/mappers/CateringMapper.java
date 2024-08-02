package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.CateringInputDto;
import com.backend.tokokantjil.dtos.outputs.CateringOutputDto;
import com.backend.tokokantjil.dtos.outputs.DishOutputDto;
import com.backend.tokokantjil.dtos.outputs.ProductOutputDto;
import com.backend.tokokantjil.models.Catering;
import com.backend.tokokantjil.models.Dish;
import com.backend.tokokantjil.models.Product;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CateringMapper {
    public static Catering fromCateringInputDtoToCatering(CateringInputDto cateringInputDto) {
        Catering catering = new Catering();

        catering.setDateAndTime(LocalDateTime.parse(cateringInputDto.dateAndTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        catering.setNumberOfPeople(cateringInputDto.numberOfPeople);
        catering.setTotalCostPrice(0);
        catering.setTotalSellPrice(0);
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
            for (Product product : catering.getProducts()) {
                productOutputDtoList.add(ProductMapper.fromProductToProductOutputDto(product));
            }
            cateringOutputDto.setProducts(productOutputDtoList);
        }
        if (catering.getDishes() != null) {
            List<DishOutputDto> dishOutputDtoList = new ArrayList<>();
            for (Dish dish : catering.getDishes()) {
                dishOutputDtoList.add(DishMapper.fromDishToDishOutputDto(dish));
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
