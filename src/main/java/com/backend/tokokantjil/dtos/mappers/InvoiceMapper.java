package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.InvoiceInputDto;
import com.backend.tokokantjil.dtos.outputs.InvoiceOutputDto;
import com.backend.tokokantjil.models.Invoice;

public class InvoiceMapper {
    public static Invoice fromInvoiceInputDtoToInvoice(InvoiceInputDto invoiceInputDto) {
        Invoice i = new Invoice();

        i.setTotalPrice(invoiceInputDto.totalPrice);
        i.setPaid(invoiceInputDto.isPaid);
        i.setNotes(invoiceInputDto.notes);
        i.setCustomer(invoiceInputDto.customer);

        return i;
    }

    public static InvoiceOutputDto fromInvoiceToInvoiceOutputDto(Invoice invoice) {
        InvoiceOutputDto invoiceOutputDto = new InvoiceOutputDto();

        invoiceOutputDto.setId(invoice.getId());
        invoiceOutputDto.setTotalPrice(invoice.getTotalPrice());
        invoiceOutputDto.setPaid(invoice.isPaid());
        invoiceOutputDto.setNotes(invoice.getNotes());
        invoiceOutputDto.setCustomer(invoice.getCustomer());

        return invoiceOutputDto;
    }

    public static Invoice fromInvoiceToUpdatedInvoice(Invoice i, Invoice iUpdate) {
        i.setTotalPrice(iUpdate.getTotalPrice());
        i.setPaid(iUpdate.isPaid());
        i.setNotes(iUpdate.getNotes());
        i.setCustomer(iUpdate.getCustomer());

        return i;
    }
}
