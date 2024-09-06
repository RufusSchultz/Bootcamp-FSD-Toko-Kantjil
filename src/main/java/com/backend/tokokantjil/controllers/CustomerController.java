package com.backend.tokokantjil.controllers;

import com.backend.tokokantjil.dtos.inputs.CustomerInputDto;
import com.backend.tokokantjil.dtos.outputs.CustomerOutputDto;
import com.backend.tokokantjil.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.backend.tokokantjil.helpers.ValidationChecker.validationChecker;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CustomerOutputDto>> getAllCustomers() {
        return ResponseEntity.ok(service.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerOutputDto> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerInputDto customerInputDto, BindingResult br) {
        validationChecker(br);
        CustomerOutputDto customerOutputDto = service.createCustomer(customerInputDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + customerOutputDto.getId()).toUriString());
        return ResponseEntity.created(uri).body(customerOutputDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        service.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerInputDto customerInputDto, BindingResult br) {
        validationChecker(br);
        CustomerOutputDto customerOutputDto = service.updateCustomer(id, customerInputDto);
        return ResponseEntity.ok(customerOutputDto);
    }

    @PostMapping("/{id}/address")
    public ResponseEntity<CustomerOutputDto> setCustomerAddress(@PathVariable Long id, @RequestParam Long addressId) {
        CustomerOutputDto customerOutputDto = service.setCustomerAddress(id, addressId);
        return ResponseEntity.ok(customerOutputDto);
    }
}
