package com.backend.tokokantjil.controllers;

import com.backend.tokokantjil.dtos.inputs.InvoiceInputDto;
import com.backend.tokokantjil.dtos.outputs.InvoiceOutputDto;
import com.backend.tokokantjil.services.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.backend.tokokantjil.helpers.ValidationChecker.validationChecker;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    private final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceOutputDto>> getAllInvoices() {
        return ResponseEntity.ok(service.getAllInvoices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceOutputDto> getInvoiceById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getInvoiceById(id));
    }

    @PostMapping
    public ResponseEntity<?> createInvoice(@Valid @RequestBody InvoiceInputDto invoiceInputDto, BindingResult br) {
        validationChecker(br);
        InvoiceOutputDto invoiceOutputDto = service.createInvoice(invoiceInputDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + invoiceOutputDto.getId()).toUriString());
        return ResponseEntity.created(uri).body(invoiceOutputDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInvoice(@PathVariable Long id) {
        service.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInvoice(@PathVariable Long id, @Valid @RequestBody InvoiceInputDto invoiceInputDto, BindingResult br) {
        validationChecker(br);
        InvoiceOutputDto invoiceOutputDto = service.updateInvoice(id, invoiceInputDto);
        return ResponseEntity.ok(invoiceOutputDto);
    }

    @PostMapping("/{id}/order")
    public ResponseEntity<String> assignOrder(@PathVariable Long id, @RequestParam Long orderId, boolean useAgreedPriceIfAny) {
        String response = service.assignOrderToInvoice(id, orderId, useAgreedPriceIfAny);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/payment")
    public ResponseEntity<String> setPayment(@PathVariable Long id, boolean hasBeenPaid) {
        String response = service.setInvoicePaymentStatus(id, hasBeenPaid);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<InvoiceOutputDto>> getInvoiceHistory(@PathVariable Long customerId) {
        List<InvoiceOutputDto> invoiceOutputDtoList = service.getAllInvoicesByCustomerId(customerId);
        return ResponseEntity.ok(invoiceOutputDtoList);
    }
}
