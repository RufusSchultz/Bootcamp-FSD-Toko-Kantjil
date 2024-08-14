package com.backend.tokokantjil.controllers;

import com.backend.tokokantjil.dtos.inputs.RoleInputDto;
import com.backend.tokokantjil.dtos.outputs.RoleOutputDto;
import com.backend.tokokantjil.dtos.outputs.RoleOutputDto;
import com.backend.tokokantjil.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.backend.tokokantjil.helpers.ValidationChecker.validationChecker;

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

//    @PostMapping
//    public ResponseEntity<?> createRole(@Valid @RequestBody RoleInputDto roleInputDto, BindingResult br) {
//        try {
//            if (validationChecker(br) == null) {
//                RoleOutputDto roleOutputDto = service.createRole(roleInputDto);
//                URI uri = URI.create(ServletUriComponentsBuilder
//                        .fromCurrentRequest()
//                        .path("/" + roleOutputDto.getRoleName()).toUriString());
//                return ResponseEntity.created(uri).body(roleOutputDto);
//            } else {
//                return validationChecker(br);
//            }
//        } catch (Exception ex) {
//            return ResponseEntity.unprocessableEntity().body("Failed to create role.");
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteRole(@PathVariable String id) {
//        service.deleteRole(id);
//        return ResponseEntity.noContent().build();
//    }

}
