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

    @PostMapping("/{dishId}/add-product")
    public ResponseEntity<?> addProductToDish(@PathVariable Long dishId, @RequestParam Long productId, double amountMultiplier) {

        //amountMultiplier only modifies productionPrice and sellPrice of Dish right now, and does nothing with stock of Product. This is for future development.
        DishOutputDto dishOutputDto = this.service.addProductToCollectionOfDish(dishId, productId, amountMultiplier);
        return ResponseEntity.ok("Added product " + productId + " to dish " + dishId + " with a multiplier of " + amountMultiplier + ".");
    }

    @PostMapping("/{id}/increase-stock")
    public ResponseEntity<String> stockDish(@PathVariable long id, @RequestParam int amount) {
        DishOutputDto dishOutputDto = service.increaseDishStock(id, amount);
        String response = "Stock increased to " + dishOutputDto.getStock() + ".";
        if (dishOutputDto.getStock() < 0) {
            response = "Stock is still less than zero! " + response;
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/decrease-stock")
    public ResponseEntity<String> consumeDish(@PathVariable long id, @RequestParam int amount) {
        DishOutputDto dishOutputDto = service.decreaseDishStock(id, amount);
        String response = "Stock decreased to " + dishOutputDto.getStock() + ".";
        if (dishOutputDto.getStock() < 0) {
            response = "Stock is now less than zero! " + response;
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/set-prices")
    public ResponseEntity<String> setDishPrices(@PathVariable long id, @RequestParam double laborCost) {
        String response = service.calculatePrices(id, laborCost);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/reset-prices")
    public ResponseEntity<DishOutputDto> resetPrices(@PathVariable long id) {
        DishOutputDto dishOutputDto = service.setPricesToZero(id);
        return ResponseEntity.ok(dishOutputDto);
    }
}
