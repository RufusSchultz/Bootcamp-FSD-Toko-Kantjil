package com.backend.tokokantjil.controllers;

import com.backend.tokokantjil.dtos.inputs.OrderInputDto;
import com.backend.tokokantjil.dtos.outputs.OrderOutputDto;
import com.backend.tokokantjil.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.backend.tokokantjil.helpers.ValidationChecker.validationChecker;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OrderOutputDto>> getAllOrders() {
        return ResponseEntity.ok(service.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderOutputDto> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOrderById(id));
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderInputDto orderInputDto, BindingResult br) {
        try {
            if (validationChecker(br) == null) {
                OrderOutputDto orderOutputDto = service.createOrder(orderInputDto);
                URI uri = URI.create(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + orderOutputDto.getId()).toUriString());
                return ResponseEntity.created(uri).body(orderOutputDto);
            } else {
                return validationChecker(br);
            }
        } catch (Exception ex) {
            return ResponseEntity.unprocessableEntity().body("Failed to create order.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@Valid @PathVariable Long id, @RequestBody OrderInputDto orderInputDto, BindingResult br) {
        if (validationChecker(br) == null) {
            OrderOutputDto orderOutputDto = service.updateOrder(id, orderInputDto);
            return ResponseEntity.ok(orderOutputDto);
        } else {
            return validationChecker(br);
        }
    }

    @PostMapping("/{id}/customer")
    public ResponseEntity<OrderOutputDto> setCustomer(@PathVariable Long id, @RequestParam Long customerId) {
        OrderOutputDto orderOutputDto = service.assignCustomerToOrder(id, customerId);
        return ResponseEntity.ok(orderOutputDto);
    }

    @PostMapping("/{id}/catering")
    public ResponseEntity<String> setCatering(@PathVariable Long id, @RequestParam Long cateringId) {
        String response = service.assignCateringToOrder(id, cateringId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<String> addRetailProduct(@PathVariable Long id, @RequestParam Long productId) {
        String response = service.addProductToOrder(id, productId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/products")
    public ResponseEntity<String> removeRetailProduct(@PathVariable Long id, @RequestParam Long productId) {
        String response = service.removeProductFromOrder(id, productId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/dishes")
    public ResponseEntity<String> addDishToOrder(@PathVariable Long id, @RequestParam Long dishId) {
        String response = service.addDishToListOfOrder(id, dishId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/dishes")
    public ResponseEntity<String> removeDishFromOrder(@PathVariable Long id, @RequestParam Long dishId) {
        String response = service.removeDishFromListOfOrder(id, dishId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/prices")
    public ResponseEntity<String> calculateOrderPrices(@PathVariable Long id) {
        String response = service.calculateOrderTotalPrices(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/prices/reset")
    public ResponseEntity<OrderOutputDto> deleteOrderPrices(@PathVariable Long id) {
        OrderOutputDto orderOutputDto = service.setOrderPricesToZero(id);
        return ResponseEntity.ok(orderOutputDto);
    }
}
