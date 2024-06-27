package com.backend.tokokantjil.dtos.inputs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class DishInputDto {
    @NotBlank
    public String name;
    @Positive
    public int servings;
    @Positive
    public double productionPrice;
    @Positive
    public double sellPrice;

    public String notes;
}
