package com.backend.tokokantjil.dtos.inputs;

import com.backend.tokokantjil.models.Role;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public class UserInputDto {
    @NotBlank
    @Size(min = 2, max = 20)
    public String username;
    @NotBlank
    public String password;
    @NotNull
    public String[] roles;
    @NotBlank
    public String firstName;
    @NotBlank
    public String lastName;
    @NotBlank
    @Email (regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    public String email;
    @NotBlank
    @Pattern(regexp = "^\\d{10}$", message = "Number must be a 10 digit long number.")
    public String phoneNumber;
    @PositiveOrZero
    public double salary;
    public String notes;



}
