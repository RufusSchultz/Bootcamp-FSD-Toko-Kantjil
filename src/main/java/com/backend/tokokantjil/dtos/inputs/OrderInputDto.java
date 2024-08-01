package com.backend.tokokantjil.dtos.inputs;

import jakarta.validation.constraints.AssertFalse;

public class OrderInputDto {

    public String title;
    @AssertFalse
    public boolean isCateringOrder;
}
