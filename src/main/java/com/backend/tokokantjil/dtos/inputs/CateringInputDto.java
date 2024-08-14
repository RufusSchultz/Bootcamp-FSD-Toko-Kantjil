package com.backend.tokokantjil.dtos.inputs;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class CateringInputDto {
    @Pattern(regexp = "^(3[01]|0[1-9]|[12][0-9])-?(1[0-2]|0[1-9])-?([0-9]{4}) (2[0-3]|[01][0-9]):?([0-5][0-9])$", message = "format must be dd-MM-yyy HH:mm")
    public String dateAndTime;
    @Positive
    public int numberOfPeople;
    @Positive
    public double agreedPrice;
    public String notes;

    public CateringInputDto() {
    }

    public CateringInputDto(String dateAndTime, int numberOfPeople, double agreedPrice, String notes) {
        this.dateAndTime = dateAndTime;
        this.numberOfPeople = numberOfPeople;
        this.agreedPrice = agreedPrice;
        this.notes = notes;
    }
}
