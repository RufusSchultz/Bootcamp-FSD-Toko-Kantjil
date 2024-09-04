package com.backend.tokokantjil.controllers;

import com.backend.tokokantjil.dtos.inputs.OrderInputDto;
import com.backend.tokokantjil.dtos.outputs.OrderOutputDto;
import com.backend.tokokantjil.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<?> createOrder(@Valid @AuthenticationPrincipal UserDetails userDetails, @RequestBody OrderInputDto orderInputDto, BindingResult br) {
        validationChecker(br);
        OrderOutputDto orderOutputDto = service.createOrder(userDetails, orderInputDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + orderOutputDto.getId()).toUriString());
        return ResponseEntity.created(uri).body(orderOutputDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@Valid @PathVariable Long id, @RequestBody OrderInputDto orderInputDto, BindingResult br) {
        validationChecker(br);
        OrderOutputDto orderOutputDto = service.updateOrder(id, orderInputDto);
        return ResponseEntity.ok(orderOutputDto);
    }

    @PostMapping("/{id}/customer")
    public ResponseEntity<OrderOutputDto> setCustomer(@PathVariable Long id, @RequestParam Long customerId) {
        OrderOutputDto orderOutputDto = service.assignCustomerToOrder(id, customerId);
        return ResponseEntity.ok(orderOutputDto);
    }

    @PostMapping("/{id}/catering")
    public ResponseEntity<OrderOutputDto> setCatering(@PathVariable Long id, @RequestParam Long cateringId) {
        OrderOutputDto orderOutputDto = service.assignCateringToOrder(id, cateringId);
        return ResponseEntity.ok(orderOutputDto);
    }

    @PostMapping("/{id}/status")
    public ResponseEntity<String> setStatus(@PathVariable Long id, @RequestParam String status) {
        String response = service.setOrderStatus(id, status);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<OrderOutputDto> addRetailProduct(@PathVariable Long id, @RequestParam Long productId) {
        OrderOutputDto orderOutputDto = service.addProductToOrder(id, productId);
        return ResponseEntity.ok(orderOutputDto);
    }

    @DeleteMapping("/{id}/products")
    public ResponseEntity<String> removeRetailProduct(@PathVariable Long id, @RequestParam Long productId) {
        String response = service.removeProductFromOrder(id, productId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/dishes")
    public ResponseEntity<OrderOutputDto> addDishToOrder(@PathVariable Long id, @RequestParam Long dishId) {
        OrderOutputDto orderOutputDto = service.addDishToListOfOrder(id, dishId);
        return ResponseEntity.ok(orderOutputDto);
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
