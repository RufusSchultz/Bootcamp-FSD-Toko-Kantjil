package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.inputs.InvoiceInputDto;
import com.backend.tokokantjil.dtos.mappers.InvoiceMapper;
import com.backend.tokokantjil.dtos.outputs.InvoiceOutputDto;
import com.backend.tokokantjil.exceptions.RecordNotFoundException;
import com.backend.tokokantjil.models.Invoice;
import com.backend.tokokantjil.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<InvoiceOutputDto> getAllInvoices() {
        List<InvoiceOutputDto> list = new ArrayList<>();
        for (Invoice i: this.invoiceRepository.findAll()) {
            list.add(InvoiceMapper.fromInvoiceToInvoiceOutputDto(i));
        }
        return list;
    }

    public InvoiceOutputDto getInvoiceById(Long id) {
        Invoice p = this.invoiceRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No invoice with id " + id + " found."));
        return InvoiceMapper.fromInvoiceToInvoiceOutputDto(p);
    }

    public InvoiceOutputDto createInvoice(InvoiceInputDto invoiceInputDto) {
        Invoice p = this.invoiceRepository.save(InvoiceMapper.fromInvoiceInputDtoToInvoice(invoiceInputDto));
        return InvoiceMapper.fromInvoiceToInvoiceOutputDto(p);
    }

    public void deleteInvoice(Long id) {
        if(this.invoiceRepository.findById(id).isPresent()) {
            this.invoiceRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No invoice with id " + id + " found.");
        }
    }

    public InvoiceOutputDto updateInvoice(Long id, InvoiceInputDto invoiceInputDto) {
        Invoice i1 = this.invoiceRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No invoice with id " + id + " found."));
        Invoice i2 = InvoiceMapper.fromInvoiceInputDtoToInvoice(invoiceInputDto);
        Invoice i3 = this.invoiceRepository.save(InvoiceMapper.fromInvoiceToUpdatedInvoice(i1, i2));

        return InvoiceMapper.fromInvoiceToInvoiceOutputDto(i3);
    }
}
