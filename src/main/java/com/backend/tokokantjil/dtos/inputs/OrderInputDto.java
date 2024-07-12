package com.backend.tokokantjil.dtos.inputs;

import com.backend.tokokantjil.enumerations.Status;
import jakarta.validation.constraints.NotBlank;

public class OrderInputDto {

    public String title;
    //custom validation
    public Status status;
    public boolean isCatering;
}
