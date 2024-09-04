package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.RoleInputDto;
import com.backend.tokokantjil.dtos.outputs.RoleOutputDto;
import com.backend.tokokantjil.models.Role;

public class RoleMapper {
    public static Role fromRoleInputDtoToRole(RoleInputDto roleInputDto) {
        Role role = new Role();

        role.setRoleName(roleInputDto.roleName);

        return role;
    }

    public static RoleOutputDto fromRoleToRoleOutputDto(Role role) {
        RoleOutputDto roleOutputDto = new RoleOutputDto();

        roleOutputDto.setRoleName(role.getRoleName());

        return roleOutputDto;
    }

    public static Role fromRoleToUpdatedRole(Role role, Role roleUpdate) {
        role.setRoleName(roleUpdate.getRoleName());

        return role;
    }
}
