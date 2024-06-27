package com.backend.tokokantjil.dtos.inputs;

import com.backend.tokokantjil.enumerations.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmployeeInputDto {
    public Long id;
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
    @NotBlank
    public Long phoneNumber;
    public String notes;
    @NotBlank
    public Role role;
    @NotBlank
    public double salary;
}
