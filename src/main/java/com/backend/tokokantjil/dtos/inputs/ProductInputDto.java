package com.backend.tokokantjil.dtos.inputs;

import com.backend.tokokantjil.enumerations.State;
import jakarta.validation.constraints.*;

public class ProductInputDto {
    @NotBlank
    public String state;
    @NotBlank
    public String name;
    @Positive
    public int amount;
    @NotBlank
    public String amountUnit;
    @PositiveOrZero
    public double buyPrice;
    @PositiveOrZero
    public double sellPrice;
    public boolean isForRetail;
    @PositiveOrZero
    public double stock;

    public String notes;
}
