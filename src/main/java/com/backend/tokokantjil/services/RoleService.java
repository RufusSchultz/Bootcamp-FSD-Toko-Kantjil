package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.mappers.RoleMapper;
import com.backend.tokokantjil.dtos.outputs.RoleOutputDto;
import com.backend.tokokantjil.models.Role;
import com.backend.tokokantjil.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleOutputDto> getAllRoles() {
        List<RoleOutputDto> list = new ArrayList<>();
        for (Role i : this.roleRepository.findAll()) {
            list.add(RoleMapper.fromRoleToRoleOutputDto(i));
        }
        return list;
    }

}
