package com.backend.tokokantjil.dtos.inputs;


import com.backend.tokokantjil.models.users.Customer;
import jakarta.validation.constraints.PositiveOrZero;

public class InvoiceInputDto {
    @PositiveOrZero
    public double totalPrice;
    public boolean isPaid;
    public String notes;
    public Customer customer;
}
