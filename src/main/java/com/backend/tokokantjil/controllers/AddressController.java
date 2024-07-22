package com.backend.tokokantjil.controllers;

import com.backend.tokokantjil.dtos.inputs.AddressInputDto;
import com.backend.tokokantjil.dtos.outputs.AddressOutputDto;
import com.backend.tokokantjil.services.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.backend.tokokantjil.helpers.ValidationChecker.validationChecker;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AddressOutputDto>> getAllAddresses() {
        return ResponseEntity.ok(service.getAllAddresss());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressOutputDto> getAddressById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAddressById(id));
    }

    @PostMapping
    public ResponseEntity<?> createAddress(@Valid @RequestBody AddressInputDto addressInputDto, BindingResult br) {
        try {
            if (validationChecker(br) == null) {
                AddressOutputDto addressOutputDto = service.createAddress(addressInputDto);
                URI uri = URI.create(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + addressOutputDto.getId()).toUriString());
                return ResponseEntity.created(uri).body(addressOutputDto);
            } else {
                return validationChecker(br);
            }
        } catch (Exception ex) {
            return ResponseEntity.unprocessableEntity().body("Failed to create address.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
        service.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddress(@Valid @PathVariable Long id, @RequestBody AddressInputDto addressInputDto, BindingResult br) {
            if (validationChecker(br) == null) {
                AddressOutputDto addressOutputDto = service.updateAddress(id, addressInputDto);
                return ResponseEntity.ok(addressOutputDto);
            } else {
                return validationChecker(br);
            }
    }
}
