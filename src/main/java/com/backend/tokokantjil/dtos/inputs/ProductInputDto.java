package com.backend.tokokantjil.dtos.inputs;

import jakarta.validation.constraints.*;

public class ProductInputDto {
    @NotBlank
    public String name;
    @NotBlank
    public String state;
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

    public ProductInputDto() {
    }

    public ProductInputDto(String name, String state, int amount, String amountUnit, double buyPrice, double sellPrice, boolean isForRetail, double stock, String notes) {
        this.name = name;
        this.state = state;
        this.amount = amount;
        this.amountUnit = amountUnit;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.isForRetail = isForRetail;
        this.stock = stock;
        this.notes = notes;
    }
}
