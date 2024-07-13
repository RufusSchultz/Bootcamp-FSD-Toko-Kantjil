package com.backend.tokokantjil.dtos.inputs;

import jakarta.validation.constraints.NotBlank;

public class RoleInputDto {
    @NotBlank
    public String roleName;

}
