package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.DishInputDto;
import com.backend.tokokantjil.dtos.outputs.DishOutputDto;
import com.backend.tokokantjil.dtos.outputs.ProductOutputDto;
import com.backend.tokokantjil.models.Dish;

import java.util.ArrayList;
import java.util.List;

public class DishMapper {

    public static Dish fromDishInputDtoToDish(DishInputDto dishInputDto) {
        Dish dish = new Dish();

        dish.setName(dishInputDto.name);
        dish.setServings(dishInputDto.servings);
        dish.setProductionPrice(dishInputDto.productionPrice);
        dish.setSellPrice(dishInputDto.sellPrice);
        dish.setNotes(dishInputDto.notes);

        return dish;
    }

    public static DishOutputDto fromDishToDishOutputDto(Dish dish) {
        DishOutputDto dishOutputDto = new DishOutputDto();

        dishOutputDto.setId(dish.getId());
        dishOutputDto.setName(dish.getName());
        dishOutputDto.setServings(dish.getServings());
        dishOutputDto.setProductionPrice(dish.getProductionPrice());
        dishOutputDto.setSellPrice(dish.getSellPrice());
        dishOutputDto.setNotes(dish.getNotes());

        if (dish.getProducts() != null) {
            List<ProductOutputDto> productOutputDtoList = new ArrayList<>();
            for (int i = dish.getProducts().size(); i < dish.getProducts().size(); i++) {
                productOutputDtoList.add(ProductMapper.fromProductToProductOutputDto(dish.getProducts().get(i)));
            }
            dishOutputDto.setProductOutputDtoList(productOutputDtoList);
        }

        return dishOutputDto;
    }

    public static Dish fromDishToUpdatedDish(Dish dish, Dish dishUpdate) {
        dish.setName(dishUpdate.getName());
        dish.setServings(dishUpdate.getServings());
        dish.setProductionPrice(dishUpdate.getProductionPrice());
        dish.setSellPrice(dishUpdate.getSellPrice());
        dish.setNotes(dishUpdate.getNotes());

        return dish;
    }

}
