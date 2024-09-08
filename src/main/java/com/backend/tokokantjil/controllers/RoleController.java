package com.backend.tokokantjil.controllers;

import com.backend.tokokantjil.dtos.outputs.RoleOutputDto;
import com.backend.tokokantjil.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RoleOutputDto>> getAllRoles() {
        return ResponseEntity.ok(service.getAllRoles());
    }

}
