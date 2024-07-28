package com.backend.tokokantjil.dtos.inputs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class CateringInputDto {

    @NotNull
    public LocalDateTime dateAndTime;
    @Positive
    public int numberOfPeople;
    @Positive
    public double agreedPrice;
    public String notes;
}
