package com.backend.tokokantjil.dtos.inputs;

import com.backend.tokokantjil.enumerations.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class ProductInputDto {
    //custom validation needed
    public State state;
    @NotBlank
    public String name;
    @Positive
    public double amount;
    @NotBlank
    public String amountUnit;
    @PositiveOrZero
    public double buyPrice;
    @PositiveOrZero
    public double sellPrice;
    @NotNull
    public boolean isForRetail;

    public String notes;
}
