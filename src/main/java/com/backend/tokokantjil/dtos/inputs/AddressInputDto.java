package com.backend.tokokantjil.dtos.inputs;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class AddressInputDto {
    @NotBlank
    public String name;
    @NotBlank
    public String street;
    @Positive
    public int houseNumber;
    public String houseNumberSuffix;
    @NotBlank
    public String postalCode;
    @NotBlank
    public String city;
    public String notes;
}
