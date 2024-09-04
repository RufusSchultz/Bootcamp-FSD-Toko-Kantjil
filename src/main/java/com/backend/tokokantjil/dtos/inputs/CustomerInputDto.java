package com.backend.tokokantjil.dtos.inputs;

import jakarta.validation.constraints.*;

public class CustomerInputDto {
    @NotBlank
    public String firstName;
    @NotBlank
    public String lastName;
    @NotBlank
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    public String email;
    @Positive
    public Long phoneNumber;
    public String notes;
}
