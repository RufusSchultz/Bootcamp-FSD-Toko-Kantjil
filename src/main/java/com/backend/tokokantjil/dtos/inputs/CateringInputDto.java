package com.backend.tokokantjil.dtos.inputs;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class CateringInputDto {
    @Pattern(regexp = "^(3[01]|0[1-9]|[12][0-9])-?(1[0-2]|0[1-9])-?([0-9]{4}) (2[0-3]|[01][0-9]):?([0-5][0-9])$", message = "format must be dd-MM-yyy HH:mm")
    public String dateAndTime;
    @Positive
    public int numberOfPeople;
    @Positive
    public double agreedPrice;
    public String notes;
}
