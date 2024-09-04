package com.backend.tokokantjil.dtos.inputs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CustomerInputDto {
    @NotBlank
    public String firstName;
    @NotBlank
    public String lastName;
    @NotBlank
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    public String email;
    @NotBlank
    @Pattern(regexp = "^\\d{10}$", message = "Number must be a 10 digit long number.")
    public Long phoneNumber;
    public String notes;

}
