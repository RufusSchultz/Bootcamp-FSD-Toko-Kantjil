package com.backend.tokokantjil.dtos.inputs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class UserInputDto {
    @NotBlank
    //zorgen dat dit uniek is!
    public String username;
    @NotBlank
    public String password;
    @NotBlank
    public String firstName;
    @NotBlank
    public String lastName;
    @Email
    public String email;
    //custom validation needed
    public Long phoneNumber;
    @PositiveOrZero
    public double salary;
    public String notes;

}
