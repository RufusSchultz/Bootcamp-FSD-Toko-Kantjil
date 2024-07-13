package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.InvoiceInputDto;
import com.backend.tokokantjil.dtos.outputs.InvoiceOutputDto;
import com.backend.tokokantjil.models.Invoice;

public class InvoiceMapper {
    public static Invoice fromInvoiceInputDtoToInvoice(InvoiceInputDto invoiceInputDto) {
        Invoice invoice = new Invoice();

        invoice.setTotalPrice(invoiceInputDto.totalPrice);
        invoice.setPaid(invoiceInputDto.isPaid);
        invoice.setNotes(invoiceInputDto.notes);

        return invoice;
    }

    public static InvoiceOutputDto fromInvoiceToInvoiceOutputDto(Invoice invoice) {
        InvoiceOutputDto invoiceOutputDto = new InvoiceOutputDto();

        invoiceOutputDto.setId(invoice.getId());
        invoiceOutputDto.setTotalPrice(invoice.getTotalPrice());
        invoiceOutputDto.setPaid(invoice.isPaid());
        invoiceOutputDto.setNotes(invoice.getNotes());

        return invoiceOutputDto;
    }

    public static Invoice fromInvoiceToUpdatedInvoice(Invoice invoice, Invoice invoiceUpdate) {
        invoice.setTotalPrice(invoiceUpdate.getTotalPrice());
        invoice.setPaid(invoiceUpdate.isPaid());
        invoice.setNotes(invoiceUpdate.getNotes());

        return invoice;
    }
}
