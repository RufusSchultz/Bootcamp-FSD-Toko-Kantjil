package com.backend.tokokantjil.controllers;

import com.backend.tokokantjil.dtos.inputs.DishInputDto;
import com.backend.tokokantjil.dtos.outputs.DishOutputDto;
import com.backend.tokokantjil.services.DishService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.backend.tokokantjil.helpers.ValidationChecker.validationChecker;

@RestController
@RequestMapping("/dishes")
public class DishController {
    private final DishService service;

    public DishController(DishService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DishOutputDto>> getAllDishes() {
        return ResponseEntity.ok(service.getEveryDish());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishOutputDto> getDish(@PathVariable Long id) {
        return ResponseEntity.ok(service.getDishById(id));
    }

    @PostMapping
    public ResponseEntity<?> createDish(@Valid @RequestBody DishInputDto dishInputDto, BindingResult br) {
        try {
            if (validationChecker(br) == null) {
                DishOutputDto dishOutputDto = service.createNewDish(dishInputDto);
                URI uri = URI.create(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + dishOutputDto.getId()).toUriString());
                return ResponseEntity.created(uri).body(dishOutputDto);
            } else {
                return validationChecker(br);
            }
        } catch (Exception ex) {
            return ResponseEntity.unprocessableEntity().body("Failed to create dish.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDish(@PathVariable Long id) {
        service.deleteDishById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDish(@Valid @PathVariable Long id, @RequestBody DishInputDto dishInputDto, BindingResult br) {
        if (validationChecker(br) == null) {
            DishOutputDto dishOutputDto = service.updateDishWithNewDishInputDto(id, dishInputDto);
            return ResponseEntity.ok(dishOutputDto);
        } else {
            return validationChecker(br);
        }
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<DishOutputDto> addProductToDish(@PathVariable Long id, @RequestParam Long productId, double amountMultiplier) {

        //Future development: make amountMultiplier modify stock of Product.
        DishOutputDto dishOutputDto = this.service.addProductToCollectionOfDish(id, productId, amountMultiplier);
        return ResponseEntity.ok(dishOutputDto);
    }

    @DeleteMapping("/{id}/products")
    public ResponseEntity<String> removeProductFromDish(@PathVariable Long id, @RequestParam Long productId) {
        String response = service.removeProductFromCollectionOfDish(id, productId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/increase-stock")
    public ResponseEntity<String> stockDish(@PathVariable long id, @RequestParam int amount) {
        String response = service.increaseDishStock(id, amount);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/decrease-stock")
    public ResponseEntity<String> consumeDish(@PathVariable long id, @RequestParam int amount) {
        String response = service.decreaseDishStock(id, amount);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/prices")
    public ResponseEntity<String> setDishPrices(@PathVariable long id, @RequestParam double laborCost) {
        String response = service.calculateDishPrices(id, laborCost);

        if (response.equals("reset prices first")) {
            return ResponseEntity.unprocessableEntity().body("Dish prices are already calculated. Reset prices first if you want to recalculate them.");
        } else {
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/{id}/prices/reset")
    public ResponseEntity<DishOutputDto> resetPrices(@PathVariable long id) {
        DishOutputDto dishOutputDto = service.setDishPricesToZero(id);
        return ResponseEntity.ok(dishOutputDto);
    }
}
