package com.backend.tokokantjil.dtos.inputs;


import com.backend.tokokantjil.models.Customer;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.PositiveOrZero;

public class InvoiceInputDto {

    public String notes;
    public boolean isPaid;
}
