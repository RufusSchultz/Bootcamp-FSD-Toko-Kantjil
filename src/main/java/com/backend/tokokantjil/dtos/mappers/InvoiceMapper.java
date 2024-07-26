package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.InvoiceInputDto;
import com.backend.tokokantjil.dtos.outputs.InvoiceOutputDto;
import com.backend.tokokantjil.models.Invoice;

public class InvoiceMapper {
    public static Invoice fromInvoiceInputDtoToInvoice(InvoiceInputDto invoiceInputDto) {
        Invoice invoice = new Invoice();

        invoice.setFinalPrice(0);
        invoice.setPaid(false);
        invoice.setNotes(invoiceInputDto.notes);

        return invoice;
    }

    public static InvoiceOutputDto fromInvoiceToInvoiceOutputDto(Invoice invoice) {
        InvoiceOutputDto invoiceOutputDto = new InvoiceOutputDto();

        invoiceOutputDto.setId(invoice.getId());
        invoiceOutputDto.setFinalPrice(invoice.getFinalPrice());
        invoiceOutputDto.setPaid(invoice.isPaid());
        invoiceOutputDto.setNotes(invoice.getNotes());
        if(invoice.getOrder() != null){
            invoiceOutputDto.setOrder(OrderMapper.fromOrderToOrderOutputDto(invoice.getOrder()));
        }

        return invoiceOutputDto;
    }

    public static Invoice fromInvoiceToUpdatedInvoice(Invoice invoice, Invoice invoiceUpdate) {

        invoice.setNotes(invoiceUpdate.getNotes());

        return invoice;
    }
}
