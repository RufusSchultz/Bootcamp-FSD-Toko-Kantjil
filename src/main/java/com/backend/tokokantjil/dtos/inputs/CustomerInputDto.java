package com.backend.tokokantjil.dtos.inputs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CustomerInputDto {
    @NotBlank
    public String firstName;
    @NotBlank
    public String lastName;
    @Email
    public String email;
    //custom validation needed
    public Long phoneNumber;
    public String notes;

}
