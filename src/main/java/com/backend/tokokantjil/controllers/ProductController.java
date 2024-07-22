package com.backend.tokokantjil.controllers;

import com.backend.tokokantjil.dtos.inputs.ProductInputDto;
import com.backend.tokokantjil.dtos.outputs.ProductOutputDto;
import com.backend.tokokantjil.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.backend.tokokantjil.helpers.ValidationChecker.validationChecker;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductOutputDto>> getAllProducts() {
        return ResponseEntity.ok(service.getEveryProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOutputDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductInputDto productInputDto, BindingResult br) {
        try {
            if (validationChecker(br) == null) {
                ProductOutputDto productOutputDto = service.createNewProduct(productInputDto);
                URI uri = URI.create(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + productOutputDto.getId()).toUriString());
                return ResponseEntity.created(uri).body(productOutputDto);
            } else {
                return validationChecker(br);
            }
        } catch (Exception ex) {
            return ResponseEntity.unprocessableEntity().body("Failed to create product.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        service.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@Valid @PathVariable Long id, @RequestBody ProductInputDto productInputDto, BindingResult br) {
        if (validationChecker(br) == null) {
            ProductOutputDto productOutputDto = service.updateProductWithNewProductInputDto(id, productInputDto);
            return ResponseEntity.ok(productOutputDto);
        } else {
            return validationChecker(br);
        }
    }

    @PostMapping("/{id}/increase-stock")
    public ResponseEntity<String> stockProduct(@PathVariable long id, @RequestParam int amount) {
        ProductOutputDto productOutputDto = service.increaseProductStock(id, amount);
        String response = "Stock increased to " + productOutputDto.getStock() + ".";
        if (productOutputDto.getStock() < 0) {
            response = "Stock is still less than zero! " + response;
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/decrease-stock")
    public ResponseEntity<String> consumeProduct(@PathVariable long id, @RequestParam int amount) {
        ProductOutputDto productOutputDto = service.decreaseProductStock(id, amount);
        String response = "Stock decreased to " + productOutputDto.getStock() + ".";
        if (productOutputDto.getStock() < 0) {
            response = "Stock is now less than zero! " + response;
        }
        return ResponseEntity.ok(response);
    }
}
