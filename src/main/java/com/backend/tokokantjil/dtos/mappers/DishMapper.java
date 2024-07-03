package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.DishInputDto;
import com.backend.tokokantjil.dtos.outputs.DishOutputDto;
import com.backend.tokokantjil.models.Dish;

public class DishMapper {

    public static Dish fromDishInputDtoToDish(DishInputDto dishInputDto) {
        Dish d = new Dish();

        d.setName(dishInputDto.name);
        d.setServings(dishInputDto.servings);
        d.setProductionPrice(dishInputDto.productionPrice);
        d.setSellPrice(dishInputDto.sellPrice);
        d.setNotes(dishInputDto.notes);

        return d;
    }

    public static DishOutputDto fromDishToDishOutputDto(Dish dish) {
        DishOutputDto dishOutputDto = new DishOutputDto();

        dishOutputDto.setId(dish.getId());
        dishOutputDto.setName(dish.getName());
        dishOutputDto.setServings(dish.getServings());
        dishOutputDto.setProductionPrice(dish.getProductionPrice());
        dishOutputDto.setSellPrice(dish.getSellPrice());
        dishOutputDto.setNotes(dish.getNotes());

        return dishOutputDto;
    }

    public static Dish fromDishToUpdatedDish(Dish d, Dish dUpdate) {
        d.setName(dUpdate.getName());
        d.setServings(dUpdate.getServings());
        d.setProductionPrice(dUpdate.getProductionPrice());
        d.setSellPrice(dUpdate.getSellPrice());
        d.setNotes(dUpdate.getNotes());

        return d;
    }

}
