package com.backend.tokokantjil.dtos.inputs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class DishInputDto {
    @NotBlank
    public String name;
    @Positive
    public int servings;
    @Positive
    public double productionPrice;
    @Positive
    public double sellPrice;
    @PositiveOrZero
    public double stock;

    public String notes;
}
