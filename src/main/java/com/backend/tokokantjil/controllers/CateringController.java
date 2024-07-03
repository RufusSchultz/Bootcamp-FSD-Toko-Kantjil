package com.backend.tokokantjil.controllers;

import com.backend.tokokantjil.dtos.inputs.CateringInputDto;
import com.backend.tokokantjil.dtos.outputs.CateringOutputDto;
import com.backend.tokokantjil.services.CateringService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.backend.tokokantjil.helpers.ValidationChecker.validationChecker;

@RestController
@RequestMapping("/caterings")
public class CateringController {
    private final CateringService service;

    public CateringController(CateringService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CateringOutputDto>> getAllCaterings() {
        return ResponseEntity.ok(service.getAllCaterings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CateringOutputDto> getCateringById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCateringById(id));
    }

    @PostMapping
    public ResponseEntity<?> createCatering(@Valid @RequestBody CateringInputDto cateringInputDto, BindingResult br) {
        try {
            if (validationChecker(br) == null) {
                CateringOutputDto cateringOutputDto = service.createCatering(cateringInputDto);
                URI uri = URI.create(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + cateringOutputDto.getId()).toUriString());
                return ResponseEntity.created(uri).body(cateringOutputDto);
            } else {
                return validationChecker(br);
            }
        } catch (Exception ex) {
            return ResponseEntity.unprocessableEntity().body("Failed to create catering.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCatering(@PathVariable Long id) {
        service.deleteCatering(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCatering(@Valid @PathVariable Long id, @RequestBody CateringInputDto cateringInputDto, BindingResult br) {
        try {
            if (validationChecker(br) == null) {
                CateringOutputDto cateringOutputDto = service.updateCatering(id, cateringInputDto);
                return ResponseEntity.ok("Updated catering " + id + ".");
            } else {
                return validationChecker(br);
            }

        } catch (Exception ex) {
            return ResponseEntity.unprocessableEntity().body("Failed to update catering.");
        }

    }
}