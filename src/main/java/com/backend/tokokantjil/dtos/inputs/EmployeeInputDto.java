package com.backend.tokokantjil.dtos.inputs;

import com.backend.tokokantjil.enumerations.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

public class EmployeeInputDto {
    @NotBlank
    public String firstName;
    @NotBlank
    public String lastName;
    @NotBlank
    public String username;
    @NotBlank
    public String password;
    @Email
    public String email;
    //custom validation needed
    public Long phoneNumber;
    public String notes;
    //custom validation needed
    public Role role;
    @PositiveOrZero
    public double salary;
}
