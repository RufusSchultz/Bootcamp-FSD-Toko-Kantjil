package com.backend.tokokantjil.controllers;

import com.backend.tokokantjil.dtos.inputs.CateringInputDto;
import com.backend.tokokantjil.dtos.outputs.CateringOutputDto;
import com.backend.tokokantjil.dtos.outputs.DishOutputDto;
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
        return ResponseEntity.ok(service.getEveryCatering());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CateringOutputDto> getCatering(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCateringById(id));
    }

    @PostMapping
    public ResponseEntity<?> createCatering(@Valid @RequestBody CateringInputDto cateringInputDto, BindingResult br) {
        try {
            if (validationChecker(br) == null) {
                CateringOutputDto cateringOutputDto = service.createNewCatering(cateringInputDto);
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
        service.deleteCateringById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCatering(@Valid @PathVariable Long id, @RequestBody CateringInputDto cateringInputDto, BindingResult br) {
            if (validationChecker(br) == null) {
                CateringOutputDto cateringOutputDto = service.updateCateringWithNewCateringInputDto(id, cateringInputDto);
                return ResponseEntity.ok(cateringOutputDto);
            } else {
                return validationChecker(br);
            }
    }

    @PostMapping("/{id}/address")
    public ResponseEntity<String> setAddress(@PathVariable Long id, @RequestParam Long addressId) {
        CateringOutputDto cateringOutputDto = service.setCateringAddress(id, addressId);
        return ResponseEntity.ok("Address " + addressId + " set for catering " + id);
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<String> addProductToCatering(@PathVariable Long id, @RequestParam Long productId) {
        CateringOutputDto cateringOutputDto = service.addProductToListOfCatering(id, productId);
        return ResponseEntity.ok("Added product to catering " + cateringOutputDto.getId());
    }

    @DeleteMapping("/{id}/products")
    public ResponseEntity<String> removeProductFromCatering(@PathVariable Long id, @RequestParam Long productId) {
        String response = service.removeProductFromListOfCatering(id, productId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/dishes")
    public ResponseEntity<String> addDishToCatering(@PathVariable Long id, @RequestParam Long dishId) {
        String response  = service.addDishToListOfCatering(id, dishId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/dishes")
    public ResponseEntity<String> removeDishFromCatering(@PathVariable Long id, @RequestParam Long dishId) {
        String response = service.removeDishFromListOfCatering(id, dishId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/prices/agreed-price")
    public ResponseEntity<String> setAgreedPrice(@PathVariable Long id, @RequestParam double agreedPrice) {
        String response = service.setAgreedPriceOfCatering(id, agreedPrice);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/prices")
    public ResponseEntity<String> calculatePrices(@PathVariable Long id, @RequestParam double laborAndMaterialCost) {
        String response = service.calculateCateringPrices(id, laborAndMaterialCost);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/prices/reset")
    public ResponseEntity<CateringOutputDto> resetPrices(@PathVariable Long id) {
        CateringOutputDto cateringOutputDto = service.setCateringPricesToZero(id);
        return ResponseEntity.ok(cateringOutputDto);
    }

}
