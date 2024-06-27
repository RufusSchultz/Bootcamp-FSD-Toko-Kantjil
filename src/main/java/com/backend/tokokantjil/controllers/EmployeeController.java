package com.backend.tokokantjil.controllers;

import com.backend.tokokantjil.dtos.inputs.EmployeeInputDto;
import com.backend.tokokantjil.dtos.outputs.EmployeeOutputDto;
import com.backend.tokokantjil.models.account.Employee;
import com.backend.tokokantjil.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.backend.tokokantjil.helpers.ValidationChecker.validationChecker;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeOutputDto>> getAllEmployees() {
        return ResponseEntity.ok(service.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeOutputDto> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeInputDto employeeInputDto, BindingResult br) {
        try {
            if (validationChecker(br) == null) {
                EmployeeOutputDto employeeOutputDto = service.createEmployee(employeeInputDto);
                URI uri = URI.create(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + employeeOutputDto.getId()).toUriString());
                return ResponseEntity.created(uri).body(employeeOutputDto);
            } else {
                return validationChecker(br);
            }
        } catch (Exception ex) {
            return ResponseEntity.unprocessableEntity().body("Failed to create employee.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@Valid @PathVariable Long id, @RequestBody EmployeeInputDto employeeInputDto, BindingResult br) {
        try {
            if (validationChecker(br) == null) {
                EmployeeOutputDto employeeOutputDto = service.updateEmployee(id, employeeInputDto);
                return ResponseEntity.ok("Updated employee " + id + ".");
            } else {
                return validationChecker(br);
            }

        } catch (Exception ex) {
            return ResponseEntity.unprocessableEntity().body("Failed to update employee.");
        }

    }
}
