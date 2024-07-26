package com.backend.tokokantjil.controllers;

import com.backend.tokokantjil.dtos.inputs.UserInputDto;
import com.backend.tokokantjil.dtos.outputs.UserOutputDto;
import com.backend.tokokantjil.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.backend.tokokantjil.helpers.ValidationChecker.validationChecker;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserOutputDto>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOutputDto> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserInputDto userInputDto, BindingResult br) {
        try {
            if (validationChecker(br) == null) {
                UserOutputDto userOutputDto = service.createUser(userInputDto);
                URI uri = URI.create(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + userOutputDto.getUsername()).toUriString());
                return ResponseEntity.created(uri).body(userOutputDto);
            } else {
                return validationChecker(br);
            }
        } catch (Exception ex) {
            return ResponseEntity.unprocessableEntity().body("Failed to create user.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateUser(@Valid @PathVariable String id, @RequestBody UserInputDto userInputDto, BindingResult br) {
            if (validationChecker(br) == null) {
                UserOutputDto userOutputDto = service.updateUser(id, userInputDto);
                return ResponseEntity.ok(userOutputDto);
            } else {
                return validationChecker(br);
            }
    }
}
